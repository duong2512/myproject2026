package com.myproject.global.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.myproject.global.exception.InternalServerException;
import com.myproject.global.util.BeanUtils;
import com.myproject.global.util.DateEx;
import com.myproject.global.util.JacksonEx;
import com.myproject.global.util.ReflectEx;
import oracle.sql.BLOB;
import oracle.sql.CLOB;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class BaseRepositoryImpl<E, I> {
    public Object excuteUsingSp(boolean isDbOracle, final int type, final Class<?> clazz, final String procedureName, final Object... params) {
        return this.excuteUsingSp(isDbOracle, (Session) ((EntityManagerImpl) BeanUtils.getBean(EntityManagerImpl.class)).getPcEntityManagerDefault().unwrap(Session.class), type, clazz, procedureName, params);
    }

    public Object excuteUsingSp(final boolean isDbOracle, Session session, final int type, final Class<?> clazz, final String procedureName, final Object... params) {
        return session.doReturningWork(new ReturningWork<Object>() {
            public Object execute(Connection connection) throws SQLException {
                Object objRet = null;
                ResultSet rs = null;
                int iTotalParam = 0;
                if (params != null) {
                    iTotalParam = params.length;
                }

                String sQueryString = StringUtils.leftPad("", isDbOracle && type != 4 ? iTotalParam + 1 : iTotalParam, "?").replace("?", ",?");
                if (sQueryString.startsWith(",")) {
                    sQueryString = sQueryString.substring(1);
                }

                if (isDbOracle) {
                    sQueryString = String.format("call %s(%s)", procedureName, sQueryString);
                } else {
                    sQueryString = String.format("{ call %s(%s) }", procedureName, sQueryString);
                }

                try {
                    CallableStatement cs = connection.prepareCall(sQueryString);
                    Throwable var8 = null;

                    try {
                        for (int i = 0; i < iTotalParam; ++i) {
                            if (params[i] == null) {
                                cs.setNull(i + 1, 0);
                            } else if (params[i].getClass().equals(Date.class)) {
                                cs.setTimestamp(i + 1, BaseRepositoryImpl.convertUtilDate2SqlTimestamp((Date) params[i]));
                            } else if (params[i].getClass().equals(java.sql.Date.class)) {
                                cs.setTimestamp(i + 1, BaseRepositoryImpl.convertSqlDate2SqlTimestamp((java.sql.Date) params[i]));
                            } else if (params[i].getClass().equals(Timestamp.class)) {
                                cs.setTimestamp(i + 1, (Timestamp) params[i]);
                            } else {
                                cs.setObject(i + 1, params[i]);
                            }
                        }

                        switch (type) {
                            case 1:
                            case 2:
                            case 5:
                            case 6:
                            case 7:
                                if (isDbOracle) {
                                    cs.registerOutParameter(iTotalParam + 1, 2012);
                                    cs.execute();
                                    rs = (ResultSet) cs.getObject(iTotalParam + 1);
                                } else {
                                    rs = cs.executeQuery();
                                }

                                if (type == 6) {
                                    ArrayNode objTemp = BaseRepositoryImpl.convertResultSetToListObjectByJackson(rs);
                                    objRet = objTemp == null ? "" : objTemp.toString();
                                } else if (type == 7) {
                                    objRet = BaseRepositoryImpl.convertResultSetToListObjectByJackson(rs);
                                } else if (type == 5) {
                                    objRet = BaseRepositoryImpl.this.convertResultSetToExcel(rs);
                                } else {
                                    objRet = BaseRepositoryImpl.this.convertResultSetToListObject(rs, clazz);
                                }
                                break;
                            case 3:
                                if (isDbOracle) {
                                    cs.registerOutParameter(iTotalParam + 1, 2012);
                                    cs.execute();
                                    rs = (ResultSet) cs.getObject(iTotalParam + 1);
                                } else {
                                    rs = cs.executeQuery();
                                }

                                if (rs != null && rs.next()) {
                                    if (rs.getObject(1) instanceof CLOB) {
                                        objRet = rs.getString(1);
                                    } else {
                                        objRet = rs.getObject(1);
                                    }
                                }
                                break;
                            case 4:
                                cs.execute();
                                objRet = true;
                                break;
                            case 8:
                                cs.registerOutParameter(iTotalParam + 1, BaseRepositoryImpl.this.convertClassToOracleTypes(clazz));
                                cs.execute();
                                objRet = cs.getObject(iTotalParam + 1);
                        }
                    } catch (Throwable var26) {
                        var8 = var26;
                        throw var26;
                    } finally {
                        if (cs != null) {
                            if (var8 != null) {
                                try {
                                    cs.close();
                                } catch (Throwable var25) {
                                    var8.addSuppressed(var25);
                                }
                            } else {
                                cs.close();
                            }
                        }

                    }
                } catch (InstantiationException | IllegalAccessException var28) {
                    throw new InternalServerException(var28.getMessage());
                } finally {
                    if (rs != null) {
                        rs.close();
                    }

                }

                return objRet;
            }
        });
    }

    public <T> List<T> excuteListObjectUsingSp(final Class<?> clazz, final String procedureName, final Object... params) {
        return (List) this.excuteUsingSp(true, 2, clazz, procedureName, params);
    }

    public <T> List<T> excuteListObjectUsingSp(boolean isDbOracle, final Class<?> clazz, final String procedureName, final Object... params) {
        return (List) this.excuteUsingSp(isDbOracle, 2, clazz, procedureName, params);
    }

    public <T> List<T> excuteListObjectUsingSp(boolean isDbOracle, Session session, final Class<?> clazz, final String procedureName, final Object... params) {
        return (List) this.excuteUsingSp(isDbOracle, session, 2, clazz, procedureName, params);
    }

    public String excuteListObjectUsingSp2JsonString(final String procedureName, final Object... params) {
        return (String) this.excuteUsingSp(true, 6, String.class, procedureName, params);
    }

    public String excuteListObjectUsingSp2JsonString(boolean isDbOracle, final String procedureName, final Object... params) {
        return (String) this.excuteUsingSp(isDbOracle, 6, String.class, procedureName, params);
    }

    public String excuteListObjectUsingSp2JsonString(boolean isDbOracle, Session session, final String procedureName, final Object... params) {
        return (String) this.excuteUsingSp(isDbOracle, session, 6, String.class, procedureName, params);
    }

    public ArrayNode excuteListObjectUsingSp2JsonArray(final String procedureName, final Object... params) {
        return (ArrayNode) this.excuteUsingSp(true, 7, ArrayNode.class, procedureName, params);
    }

    public JsonNode excuteListObjectUsingSp2JsonNode(final String procedureName, final Object... params) {
        ArrayNode anTemp = this.excuteListObjectUsingSp2JsonArray(procedureName, params);
        return anTemp != null && !anTemp.isEmpty() ? anTemp.get(0) : null;
    }

    public ArrayNode excuteListObjectUsingSp2JsonArray(boolean isDbOracle, final String procedureName, final Object... params) {
        return (ArrayNode) this.excuteUsingSp(isDbOracle, 7, ArrayNode.class, procedureName, params);
    }

    public ArrayNode excuteListObjectUsingSp2JsonArray(boolean isDbOracle, Session session, final String procedureName, final Object... params) {
        return (ArrayNode) this.excuteUsingSp(isDbOracle, session, 7, ArrayNode.class, procedureName, params);
    }

    public byte[] excuteListObjectUsingSp2Excel(final String procedureName, final Object... params) {
        return (byte[]) ((byte[]) this.excuteUsingSp(true, 5, byte[].class, procedureName, params));
    }

    public byte[] excuteListObjectUsingSp2Excel(boolean isDbOracle, final String procedureName, final Object... params) {
        return (byte[]) ((byte[]) this.excuteUsingSp(isDbOracle, 5, byte[].class, procedureName, params));
    }

    public byte[] excuteListObjectUsingSp2Excel(boolean isDbOracle, Session session, final String procedureName, final Object... params) {
        return (byte[]) ((byte[]) this.excuteUsingSp(isDbOracle, session, 5, byte[].class, procedureName, params));
    }

    public <T> T excuteObjectUsingSp(final Class<?> clazz, final String procedureName, Object... params) {
        List<T> lstRet = (List) this.excuteUsingSp(true, 1, clazz, procedureName, params);
        return lstRet != null && !lstRet.isEmpty() ? lstRet.get(0) : null;
    }

    public <T> T excuteObjectUsingSp(boolean isDbOracle, final Class<?> clazz, final String procedureName, Object... params) {
        List<T> lstRet = (List) this.excuteUsingSp(isDbOracle, 1, clazz, procedureName, params);
        return lstRet != null && !lstRet.isEmpty() ? lstRet.get(0) : null;
    }

    public <T> T excuteObjectUsingSp(boolean isDbOracle, Session session, final Class<?> clazz, final String procedureName, Object... params) {
        List<T> lstRet = (List) this.excuteUsingSp(isDbOracle, session, 1, clazz, procedureName, params);
        return lstRet != null && !lstRet.isEmpty() ? lstRet.get(0) : null;
    }

    public boolean excuteInsertUpdateDeleteUsingSp(final String procedureName, final Object... params) {
        return (Boolean) this.excuteUsingSp(true, 4, (Class) null, procedureName, params);
    }

    public boolean excuteInsertUpdateDeleteUsingSp(boolean isDbOracle, final String procedureName, final Object... params) {
        return (Boolean) this.excuteUsingSp(isDbOracle, 4, (Class) null, procedureName, params);
    }

    public boolean excuteInsertUpdateDeleteUsingSp(boolean isDbOracle, Session session, final String procedureName, final Object... params) {
        return (Boolean) this.excuteUsingSp(isDbOracle, session, 4, (Class) null, procedureName, params);
    }

    public <R> R excuteReturnDataUsingSp(final R defaultValue, final String procedureName, final Object... params) {
        Object objRet = this.excuteUsingSp(true, 3, (Class) null, procedureName, params);
        return objRet == null ? defaultValue : (R) objRet;
    }

    public <R> R excuteReturnDataUsingSp(boolean isDbOracle, final R defaultValue, final String procedureName, final Object... params) {
        Object objRet = this.excuteUsingSp(isDbOracle, 3, (Class) null, procedureName, params);
        return objRet == null ? defaultValue : (R) objRet;
    }

    public <R> R excuteReturnDataUsingSp(boolean isDbOracle, Session session, final R defaultValue, final String procedureName, final Object... params) {
        Object objRet = this.excuteUsingSp(isDbOracle, session, 3, (Class) null, procedureName, params);
        return objRet == null ? defaultValue : (R) objRet;
    }

    public static Timestamp convertUtilDate2SqlTimestamp(Date tInput) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(tInput);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp convertSqlDate2SqlTimestamp(java.sql.Date tInput) {
        return new Timestamp(DateEx.getTimeEx(tInput));
    }

    private int convertClassToOracleTypes(Class<?> clazz) {
        if (clazz == Number.class) {
            return 2;
        } else {
            return clazz == Date.class ? 91 : 12;
        }
    }

    private static ArrayNode convertResultSetToListObjectByJackson(ResultSet rsInput) throws SQLException {
        ArrayNode lstRet = null;

        Object var2;
        try {
            if (rsInput != null) {
                lstRet = new ObjectMapper().createArrayNode();
                String columnName = null;

                while (rsInput.next()) {
                    ObjectNode joRow = new ObjectMapper().createObjectNode();
                    int totalRows = rsInput.getMetaData().getColumnCount();

                    for (int i = 0; i < totalRows; ++i) {
                        columnName = rsInput.getMetaData().getColumnLabel(i + 1);

                        if (rsInput.getObject(i + 1) != null) {
                            if (columnName.toLowerCase().startsWith("json___")) {
                                joRow.set(removeColumnNameJson(columnName), (JsonNode) JacksonEx.getInstance().string2TypeReference(rsInput.getString(i + 1), new TypeReference<JsonNode>() {
                                }));
                            } else if (!rsInput.getObject(i + 1).getClass().equals(Date.class) && !rsInput.getObject(i + 1).getClass().equals(java.sql.Date.class) && !rsInput.getObject(i + 1).getClass().equals(Timestamp.class)) {
                                if (!rsInput.getObject(i + 1).getClass().equals(Byte.TYPE) && !rsInput.getObject(i + 1).getClass().equals(Byte.class)) {
                                    if (!rsInput.getObject(i + 1).getClass().equals(byte[].class) && !rsInput.getObject(i + 1).getClass().equals(Byte[].class) && !rsInput.getObject(i + 1).getClass().equals(BLOB.class)) {
                                        if (!rsInput.getObject(i + 1).getClass().equals(Integer.TYPE) && !rsInput.getObject(i + 1).getClass().equals(Integer.class) && !rsInput.getObject(i + 1).getClass().equals(Long.TYPE) && !rsInput.getObject(i + 1).getClass().equals(Long.class) && !rsInput.getObject(i + 1).getClass().equals(BigInteger.class) && !rsInput.getObject(i + 1).getClass().equals(Short.class)) {
                                            if (!rsInput.getObject(i + 1).getClass().equals(BigDecimal.class) && !rsInput.getObject(i + 1).getClass().equals(Float.TYPE) && !rsInput.getObject(i + 1).getClass().equals(Float.class) && !rsInput.getObject(i + 1).getClass().equals(Double.TYPE) && !rsInput.getObject(i + 1).getClass().equals(Double.class) && !rsInput.getObject(i + 1).getClass().equals(Number.class)) {
                                                if (!rsInput.getObject(i + 1).getClass().equals(Boolean.TYPE) && !rsInput.getObject(i + 1).getClass().equals(Boolean.class)) {
                                                    joRow.put(columnName, rsInput.getString(i + 1));
                                                } else {
                                                    joRow.put(columnName, rsInput.getString(i + 1));
                                                }
                                            } else if (rsInput.getString(i + 1).contains(".")) {
                                                joRow.put(columnName, rsInput.getString(i + 1));
                                            } else {
                                                joRow.put(columnName, rsInput.getString(i + 1));
                                            }
                                        } else {
                                            joRow.put(columnName, rsInput.getString(i + 1));
                                        }
                                    } else {
                                        joRow.put(columnName, DatatypeConverter.printBase64Binary(rsInput.getBytes(i + 1)));
                                    }
                                } else {
                                    joRow.put(columnName, DatatypeConverter.printByte(rsInput.getByte(i + 1)));
                                }
                            } else {
                                joRow.put(columnName, rsInput.getTimestamp(i + 1).toString());
                            }
                        }
                    }

                    lstRet.add(joRow);
                }

                return lstRet;
            }

            var2 = lstRet;
        } finally {
            if (rsInput != null) {
                rsInput.close();
            }

        }

        return (ArrayNode) var2;
    }

    private byte[] convertResultSetToExcel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (columnCount < 1) {
            throw new RuntimeException("Không lấy được cột dữ liệu nào hợp lệ.");
        } else {
            try {
                HSSFWorkbook workbook = new HSSFWorkbook();
                Throwable var5 = null;

                try {
                    HSSFFont boldFont = workbook.createFont();
                    boldFont.setBold(true);
                    HSSFSheet sheet = workbook.createSheet("sheet");
                    HSSFRow row = sheet.createRow(0);
                    HSSFCellStyle style = workbook.createCellStyle();
                    style.setFont(boldFont);

                    HSSFCell cell;
                    int currentRow;
                    for (currentRow = 0; currentRow < columnCount; ++currentRow) {
                        String title = metaData.getColumnLabel(currentRow + 1);
                        cell = row.createCell(currentRow);
                        cell.setCellValue(title);
                        cell.setCellStyle(style);
                    }

                    currentRow = 1;
                    if (!resultSet.next()) {
                        throw new RuntimeException("Không lấy được dòng dữ liệu nào hợp lệ.");
                    } else {
                        int colIndex;
                        do {
                            row = sheet.createRow(currentRow++);

                            for (colIndex = 0; colIndex < columnCount; ++colIndex) {
                                Object value = resultSet.getObject(colIndex + 1);
                                cell = row.createCell(colIndex);
                                if (value == null) {
                                    cell.setCellValue("");
                                } else if (value instanceof Calendar) {
                                    cell.setCellValue((Calendar) value);
                                } else if (value instanceof Date) {
                                    cell.setCellValue((Date) value);
                                } else if (value instanceof String) {
                                    cell.setCellValue((String) value);
                                } else if (value instanceof Boolean) {
                                    cell.setCellValue((Boolean) value);
                                } else if (value instanceof Double) {
                                    cell.setCellValue((Double) value);
                                } else if (value instanceof BigDecimal) {
                                    cell.setCellValue(((BigDecimal) value).doubleValue());
                                } else {
                                    cell.setCellValue(resultSet.getString(colIndex + 1));
                                }
                            }
                        } while (resultSet.next());

                        for (colIndex = 0; colIndex < columnCount; ++colIndex) {
                            sheet.autoSizeColumn(colIndex);
                        }

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        workbook.write(baos);
                        byte[] var15 = baos.toByteArray();
                        return var15;
                    }
                } catch (Throwable var25) {
                    var5 = var25;
                    throw var25;
                } finally {
                    if (workbook != null) {
                        if (var5 != null) {
                            try {
                                workbook.close();
                            } catch (Throwable var24) {
                                var5.addSuppressed(var24);
                            }
                        } else {
                            workbook.close();
                        }
                    }

                }
            } catch (Exception var27) {
                throw new InternalServerException(var27);
            }
        }
    }

    public <T> List<T> convertResultSetToListObject(ResultSet rsInput, Class<?> clazz) throws IllegalAccessException, SQLException, InstantiationException {
        List<T> lstRet = null;

        ResultSetMetaData rsmd;
        try {
            if (rsInput != null) {
                lstRet = new ArrayList();
                rsmd = rsInput.getMetaData();
                List<Field> fields = new ArrayList();
                ReflectEx.getClassFieldsEx(fields, clazz);
                boolean isConvertSuccess = false;

                while (rsInput.next()) {
                    T objRet = ReflectEx.getClassNewInstanceEx(clazz);
                    isConvertSuccess = false;

                    for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                        if (rsInput.getObject(i) != null) {

                            for (Field f : fields) {
                                String columnName = ReflectEx.getDbColumnMapper(f);
                                if (columnName.equalsIgnoreCase(rsmd.getColumnName(i))) {
                                    isConvertSuccess = true;
                                    switch (ReflectEx.getFieldTypeCanonicalNameEx(f)) {
                                        case "java.lang.Short":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getShort(i));
                                            break;
                                        case "int":
                                        case "java.lang.Integer":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getInt(i));
                                            break;
                                        case "long":
                                        case "java.lang.Long":
                                        case "java.math.BigInteger":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getLong(i));
                                            break;
                                        case "float":
                                        case "java.lang.Float":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getFloat(i));
                                            break;
                                        case "double":
                                        case "java.lang.Double":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getDouble(i));
                                            break;
                                        case "java.math.BigDecimal":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getBigDecimal(i));
                                            break;
                                        case "java.util.Date":
                                        case "java.sql.Date":
                                        case "java.sql.Timestamp":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getTimestamp(i));
                                            break;
                                        case "boolean":
                                        case "java.lang.Boolean":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getBoolean(i));
                                            break;
                                        case "byte":
                                        case "java.lang.Byte":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getByte(i));
                                            break;
                                        case "byte[]":
                                        case "java.lang.Byte[]":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getBytes(i));
                                            break;
                                        case "string":
                                        case "java.lang.String":
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getString(i));
                                            break;
                                        default:
                                            ReflectEx.setFieldValueEx(objRet, f, rsInput.getObject(i));
                                    }
                                }
                            }
                        }
                    }

                    if (isConvertSuccess) {
                        lstRet.add(objRet);
                    }
                }

                return lstRet;
            }

            rsmd = (ResultSetMetaData) lstRet;
        } finally {
            if (rsInput != null) {
                rsInput.close();
            }

        }

        return (List<T>) rsmd;
    }

    private static String removeColumnNameJson(String dbColumnName) {
        dbColumnName = StringUtils.trimToEmpty(dbColumnName);
        if (dbColumnName.toLowerCase().startsWith("json___")) {
            dbColumnName = dbColumnName.substring("json___".length());
        }

        return dbColumnName;
    }
}