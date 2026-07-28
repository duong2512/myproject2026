package com.myproject.global.util;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.myproject.global.exception.BadRequestException;
import com.myproject.global.exception.InternalServerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class JacksonEx {
    protected static final Logger log4j = LoggerFactory.getLogger(JacksonEx.class);
    public static final ObjectMapper MAPPER_WITHOUT_CONFIG = new ObjectMapper();
    private static JacksonEx instance = new JacksonEx();

    private JacksonEx() {
    }

    public static JacksonEx getInstance() {
        return instance;
    }

    public ObjectMapper getObjectMapper() {
        ObjectMapper omMap = new ObjectMapper();
        omMap.configOverride(BigDecimal.class).setFormat(Value.forShape(Shape.STRING));
        omMap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        omMap.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
            private static final long serialVersionUID = 1L;

            public Object findFilterId(Annotated a) {
                return null;
            }
        });
        return omMap;
    }

    public ObjectWriter getObjectWriter(String sFilterId, String[] arrFilterOutAllExcept, String[] arrSerializeAllExcept) {
        ObjectMapper omMap = new ObjectMapper();
        omMap.configOverride(BigDecimal.class).setFormat(Value.forShape(Shape.STRING));
        omMap.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        if (!StringUtils.isEmpty(sFilterId)) {
            SimpleBeanPropertyFilter simpleBeanPropertyFilter;
            SimpleFilterProvider filterProvider;
            if (!CheckEx.getInstance().checkArrayIsEmpty(arrFilterOutAllExcept)) {
                simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(arrFilterOutAllExcept);
                filterProvider = (new SimpleFilterProvider()).addFilter(sFilterId, simpleBeanPropertyFilter);
                return omMap.writer(filterProvider);
            }

            if (!CheckEx.getInstance().checkArrayIsEmpty(arrSerializeAllExcept)) {
                simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(arrSerializeAllExcept);
                filterProvider = (new SimpleFilterProvider()).addFilter(sFilterId, simpleBeanPropertyFilter);
                return omMap.writer(filterProvider);
            }
        }

        omMap.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
            private static final long serialVersionUID = 1L;

            public Object findFilterId(Annotated a) {
                return null;
            }
        });
        return omMap.writer(new SimpleFilterProvider());
    }

    public <T> T objectNode2Object(ObjectNode onInput, Class<T> clazz) {
        T objRet = null;

        try {
            if (onInput != null) {
                objRet = this.getObjectMapper().setSerializationInclusion(Include.NON_NULL).treeToValue(onInput, clazz);
            }

            return objRet;
        } catch (JsonProcessingException var5) {
            throw new InternalServerException(var5);
        }
    }

    public <T> List<T> objectNode2ListObject(ObjectNode onInput, Class<T> clazz) {
        return (List) this.objectNode2Object(onInput, clazz);
    }

    public String object2String(Object objInput) {
        return this.object2String(objInput, (String) null, (String[]) null, (String[]) null);
    }

    public String object2String(Object objInput, String sFilterId, String[] arrFilterOutAllExcept, String[] arrSerializeAllExcept) {
        String sRet = null;

        try {
            if (objInput != null) {
                sRet = this.getObjectWriter(sFilterId, arrFilterOutAllExcept, arrSerializeAllExcept).writeValueAsString(objInput);
            }

            return sRet;
        } catch (JsonProcessingException var7) {
            throw new InternalServerException(var7);
        }
    }

    public ObjectNode object2ObjectNode(Object onInput) {
        return this.object2ObjectNode(onInput, (String) null, (String[]) null, (String[]) null);
    }

    public ObjectNode object2ObjectNode(Object onInput, String sFilterId, String[] arrFilterOutAllExcept, String[] arrSerializeAllExcept) {
        ObjectNode objRet = null;

        try {
            if (onInput != null) {
                objRet = (ObjectNode) this.getObjectMapper().readTree(this.getObjectWriter(sFilterId, arrFilterOutAllExcept, arrSerializeAllExcept).writeValueAsString(onInput));
            }

            return objRet;
        } catch (IOException var7) {
            throw new InternalServerException(var7);
        }
    }

    public <T> T string2Object(String sInput, Class<T> clazz) {
        T objRet = null;

        try {
            if (!StringUtils.isEmpty(sInput)) {
                objRet = this.getObjectMapper().readValue(sInput, clazz);
            }

            return objRet;
        } catch (IOException var5) {
            throw new InternalServerException(var5);
        }
    }

    public <T> T linkedHashMap2Object(Object objInput, Class<T> clazz) {
        T objRet = null;
        if (objInput != null) {
            objRet = this.getObjectMapper().convertValue(objInput, clazz);
        }

        return objRet;
    }

    public <T> List<T> linkedHashMap2ListObject(Object objInput, Class<List<T>> clazz) {
        List<T> lstRet = null;
        if (objInput != null) {
            lstRet = (List) this.getObjectMapper().convertValue(objInput, clazz);
        }

        return lstRet;
    }

    public <T> T string2TypeReference(String sInput, TypeReference<T> typeReference) {
        T objRet = null;

        try {
            if (!StringUtils.isEmpty(sInput)) {
                objRet = this.getObjectMapper().readValue(sInput, typeReference);
            }

            return objRet;
        } catch (IOException var5) {
            throw new InternalServerException(var5);
        }
    }

    public List<Map<String, Object>> arrayNode2ListHashMapFieldName(ArrayNode objArrayNode) {
        List<Map<String, Object>> lstRet = new ArrayList();
        if (objArrayNode == null) {
            return lstRet;
        } else {
            Iterator var7 = objArrayNode.iterator();

            while (var7.hasNext()) {
                JsonNode jsonNode = (JsonNode) var7.next();
                Map<String, Object> mapTemp = new HashMap();
                Iterator<String> fieldNames = jsonNode.fieldNames();

                while (fieldNames.hasNext()) {
                    String fieldName = (String) fieldNames.next();
                    JsonNode fieldNode = jsonNode.get(fieldName);
                    if (fieldNode.isLong()) {
                        mapTemp.put(fieldName, fieldNode.longValue());
                    } else if (fieldNode.isNumber()) {
                        mapTemp.put(fieldName, fieldNode.numberValue());
                    } else if (fieldNode.isBoolean()) {
                        mapTemp.put(fieldName, fieldNode.booleanValue());
                    } else if (fieldNode.isArray()) {
                        mapTemp.put(fieldName, fieldNode);
                    } else {
                        mapTemp.put(fieldName, fieldNode.asText());
                    }
                }

                lstRet.add(mapTemp);
            }

            return lstRet;
        }
    }

    public static String convertJsonPath2JacksonPath(String jsonPtrExpr) {
        jsonPtrExpr = StringUtils.trimToEmpty(jsonPtrExpr);
        if (jsonPtrExpr.startsWith("$")) {
            jsonPtrExpr = jsonPtrExpr.substring(1);
        }

        if (!jsonPtrExpr.startsWith("/")) {
            jsonPtrExpr = "/" + jsonPtrExpr;
        }

        jsonPtrExpr = jsonPtrExpr.replace(".", "/");
        jsonPtrExpr = jsonPtrExpr.replace("[%s]", "[0]");
        jsonPtrExpr = jsonPtrExpr.replace("[", "/");
        jsonPtrExpr = jsonPtrExpr.replace("]", "/");

        for (jsonPtrExpr = jsonPtrExpr.replace("//", "/"); jsonPtrExpr.endsWith("/"); jsonPtrExpr = jsonPtrExpr.substring(0, jsonPtrExpr.length() - 1)) {
        }

        return jsonPtrExpr;
    }

    public static void setJsonPointerValue(ObjectNode node, String jsonPtrExpr, Object value) {
        jsonPtrExpr = convertJsonPath2JacksonPath(jsonPtrExpr);
        if (value != null) {
            JsonNode jn;
            if (value instanceof JsonNode) {
                jn = (JsonNode) value;
            } else {
                jn = MAPPER_WITHOUT_CONFIG.valueToTree(value);
            }

            setJsonPointerValue(node, JsonPointer.compile(jsonPtrExpr), jn);
        }
    }

    public static void setJsonPointerValue(ObjectNode node, JsonPointer pointer, JsonNode value) {
        JsonPointer parentPointer = pointer.head();
        JsonNode parentNode = node.at(parentPointer);
        String fieldName = pointer.last().toString().substring(1);
        if (((JsonNode) parentNode).isMissingNode() || ((JsonNode) parentNode).isNull()) {
            parentNode = StringUtils.isNumeric(fieldName) ? MAPPER_WITHOUT_CONFIG.createArrayNode() : MAPPER_WITHOUT_CONFIG.createObjectNode();
            setJsonPointerValue(node, (JsonPointer) parentPointer, (JsonNode) parentNode);
        }

        if (((JsonNode) parentNode).isArray()) {
            ArrayNode arrayNode = (ArrayNode) parentNode;
            int index = Integer.parseInt(fieldName);

            for (int i = arrayNode.size(); i <= index; ++i) {
                arrayNode.addNull();
            }

            arrayNode.set(index, value);
        } else {
            if (!((JsonNode) parentNode).isObject()) {
                throw new IllegalArgumentException("`" + fieldName + "` can't be set for parent node `" + parentPointer + "` because parent is not a container but " + ((JsonNode) parentNode).getNodeType().name());
            }

            ((ObjectNode) parentNode).set(fieldName, value);
        }

    }

    public static String getJsonNodeByFieldNameAsText(JsonNode jnInput, String fieldName) {
        return getJsonNodeByFieldNameAsText(jnInput, fieldName, (String) null);
    }

    public static String getJsonNodeByFieldNameAsText(JsonNode jnInput, String fieldName, String defaultValue) {
        String sRet = null;
        if (jnInput != null) {
            sRet = getJsonNodeAsText(jnInput.get(fieldName));
        }

        if (StringUtils.isBlank(sRet)) {
            sRet = defaultValue;
        }

        return sRet;
    }

    public static String getJsonNodeByFieldNameAsTextWithRequired(JsonNode jnInput, String fieldName, String label) {
        label = StringUtils.isBlank(label) ? fieldName : label;
        String sRet = getJsonNodeByFieldNameAsText(jnInput, fieldName);
        if (StringUtils.isBlank(sRet)) {
            throw new BadRequestException("[%s] bắt buộc phải nhập.", new Object[]{label});
        } else {
            return sRet;
        }
    }

    public static String getJsonNodeByJsonPtrExprAsText(JsonNode jnInput, String jsonPtrExpr) {
        return getJsonNodeByJsonPtrExprAsText(jnInput, jsonPtrExpr, (String) null);
    }

    public static String getJsonNodeByJsonPtrExprAsText(JsonNode jnInput, String[] jsonPtrExpr) {
        String sRet = null;
        String[] var3 = jsonPtrExpr;
        int var4 = jsonPtrExpr.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String item = var3[var5];
            sRet = getJsonNodeByJsonPtrExprAsText(jnInput, item, (String) null);
            if (!StringUtils.isBlank(sRet)) {
                return sRet;
            }
        }

        return sRet;
    }

    public static String getJsonNodeByJsonPtrExprAsText(JsonNode jnInput, String jsonPtrExpr, String defaultValue) {
        String sRet = null;
        if (jnInput != null) {
            sRet = getJsonNodeAsText(jnInput.at(convertJsonPath2JacksonPath(jsonPtrExpr)));
        }

        if (StringUtils.isBlank(sRet)) {
            sRet = defaultValue;
        }

        return sRet;
    }

    public static String getJsonNodeByJsonPtrExprAsTextWithRequired(JsonNode jnInput, String jsonPtrExpr, String label) {
        label = StringUtils.isBlank(label) ? jsonPtrExpr : label;
        String sRet = getJsonNodeByJsonPtrExprAsText(jnInput, jsonPtrExpr);
        if (StringUtils.isBlank(sRet)) {
            throw new BadRequestException("[%s] bắt buộc phải nhập.", new Object[]{label});
        } else {
            return sRet;
        }
    }

    public static JsonNode getJsonNodeByJsonPtrExprAsJsonNode(JsonNode jnInput, String jsonPtrExpr, JsonNode jnDefault) {
        JsonNode jnRet = null;
        if (jnInput != null) {
            jnRet = jnInput.at(convertJsonPath2JacksonPath(jsonPtrExpr));
        }

        return jnRet == null ? jnDefault : jnRet;
    }

    public static String getJsonNodeAsText(JsonNode jnInput) {
        String sRet = null;
        return (String) (jnInput != null && !jnInput.isNull() && !jnInput.isMissingNode() ? jnInput.asText() : sRet);
    }

    public static JsonNode removeJsonNodeIsNull(JsonNode jnInput) {
        if (jnInput == null) {
            return null;
        } else {
            Iterator<JsonNode> it = jnInput.iterator();

            while (it.hasNext()) {
                JsonNode jnChild = (JsonNode) it.next();
                if (jnChild.isNull()) {
                    it.remove();
                } else {
                    removeJsonNodeIsNull(jnChild);
                }
            }

            return jnInput;
        }
    }

    static {
        MAPPER_WITHOUT_CONFIG.configOverride(BigDecimal.class).setFormat(Value.forShape(Shape.STRING));
    }
}
