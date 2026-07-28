//package com.myproject.global.configuration;
//
//import lombok.Generated;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(
//        prefix = "app"
//)
//public class ApplicationProperties {
//    private CurrentApp currentApp = new CurrentApp();
//    private Paging paging;
//    private Restapi restapi = new Restapi();
//    private GuavaCache guavaCache = new GuavaCache();
//
//    public Restapi getRestapi() {
//        if (this.restapi == null) {
//            this.restapi = new Restapi();
//        }
//
//        this.restapi.closeIdleConnectionsInMs = this.restapi.closeIdleConnectionsInMs > 0 ? this.restapi.closeIdleConnectionsInMs : 180000;
//        this.restapi.connectionManagerShutdownInMs = this.restapi.connectionManagerShutdownInMs > 0 ? this.restapi.connectionManagerShutdownInMs : 300000;
//        this.restapi.connectTimeoutInMs = this.restapi.connectTimeoutInMs > 0 ? this.restapi.connectTimeoutInMs : '\uea60';
//        this.restapi.maxConnection = this.restapi.maxConnection > 0 ? this.restapi.maxConnection : 1000;
//        this.restapi.maxPerRoute = this.restapi.maxPerRoute > 0 ? this.restapi.maxPerRoute : 100;
//        return this.restapi;
//    }
//
//    @Generated
//    public ApplicationProperties() {
//    }
//
//    @Generated
//    public boolean equals(final Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof ApplicationProperties)) {
//            return false;
//        } else {
//            ApplicationProperties other = (ApplicationProperties)o;
//            if (!other.canEqual(this)) {
//                return false;
//            } else {
//                label59: {
//                    Object this$currentApp = this.getCurrentApp();
//                    Object other$currentApp = other.getCurrentApp();
//                    if (this$currentApp == null) {
//                        if (other$currentApp == null) {
//                            break label59;
//                        }
//                    } else if (this$currentApp.equals(other$currentApp)) {
//                        break label59;
//                    }
//
//                    return false;
//                }
//
//                Object this$paging = this.getPaging();
//                Object other$paging = other.getPaging();
//                if (this$paging == null) {
//                    if (other$paging != null) {
//                        return false;
//                    }
//                } else if (!this$paging.equals(other$paging)) {
//                    return false;
//                }
//
//                Object this$restapi = this.getRestapi();
//                Object other$restapi = other.getRestapi();
//                if (this$restapi == null) {
//                    if (other$restapi != null) {
//                        return false;
//                    }
//                } else if (!this$restapi.equals(other$restapi)) {
//                    return false;
//                }
//
//                Object this$guavaCache = this.getGuavaCache();
//                Object other$guavaCache = other.getGuavaCache();
//                if (this$guavaCache == null) {
//                    if (other$guavaCache != null) {
//                        return false;
//                    }
//                } else if (!this$guavaCache.equals(other$guavaCache)) {
//                    return false;
//                }
//
//                return true;
//            }
//        }
//    }
//
//    @Generated
//    protected boolean canEqual(final Object other) {
//        return other instanceof ApplicationProperties;
//    }
//
//    @Generated
//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        Object $currentApp = this.getCurrentApp();
//        result = result * 59 + ($currentApp == null ? 43 : $currentApp.hashCode());
//        Object $paging = this.getPaging();
//        result = result * 59 + ($paging == null ? 43 : $paging.hashCode());
//        Object $restapi = this.getRestapi();
//        result = result * 59 + ($restapi == null ? 43 : $restapi.hashCode());
//        Object $guavaCache = this.getGuavaCache();
//        result = result * 59 + ($guavaCache == null ? 43 : $guavaCache.hashCode());
//        return result;
//    }
//
//    @Generated
//    public String toString() {
//        return "ApplicationProperties(currentApp=" + this.getCurrentApp() + ", paging=" + this.getPaging() + ", restapi=" + this.getRestapi() + ", guavaCache=" + this.getGuavaCache() + ")";
//    }
//
//    @Generated
//    public CurrentApp getCurrentApp() {
//        return this.currentApp;
//    }
//
//    @Generated
//    public void setCurrentApp(final CurrentApp currentApp) {
//        this.currentApp = currentApp;
//    }
//
//    @Generated
//    public Paging getPaging() {
//        return this.paging;
//    }
//
//    @Generated
//    public void setPaging(final Paging paging) {
//        this.paging = paging;
//    }
//
//    @Generated
//    public void setRestapi(final Restapi restapi) {
//        this.restapi = restapi;
//    }
//
//    @Generated
//    public GuavaCache getGuavaCache() {
//        return this.guavaCache;
//    }
//
//    @Generated
//    public void setGuavaCache(final GuavaCache guavaCache) {
//        this.guavaCache = guavaCache;
//    }
//
//    public static class GuavaCache {
//        private Long menuBackEndByJwtMaximumSize = 1000L;
//        private Long menuBackEndByJwtExpireAfterWriteMinutes = 10L;
//        private Long menuFrontEndByUidMaximumSize = 1000L;
//        private Long menuFrontEndByUidExpireAfterWriteMinutes = 10L;
//
//        @Generated
//        public GuavaCache() {
//        }
//
//        @Generated
//        public Long getMenuBackEndByJwtMaximumSize() {
//            return this.menuBackEndByJwtMaximumSize;
//        }
//
//        @Generated
//        public Long getMenuBackEndByJwtExpireAfterWriteMinutes() {
//            return this.menuBackEndByJwtExpireAfterWriteMinutes;
//        }
//
//        @Generated
//        public Long getMenuFrontEndByUidMaximumSize() {
//            return this.menuFrontEndByUidMaximumSize;
//        }
//
//        @Generated
//        public Long getMenuFrontEndByUidExpireAfterWriteMinutes() {
//            return this.menuFrontEndByUidExpireAfterWriteMinutes;
//        }
//
//        @Generated
//        public void setMenuBackEndByJwtMaximumSize(final Long menuBackEndByJwtMaximumSize) {
//            this.menuBackEndByJwtMaximumSize = menuBackEndByJwtMaximumSize;
//        }
//
//        @Generated
//        public void setMenuBackEndByJwtExpireAfterWriteMinutes(final Long menuBackEndByJwtExpireAfterWriteMinutes) {
//            this.menuBackEndByJwtExpireAfterWriteMinutes = menuBackEndByJwtExpireAfterWriteMinutes;
//        }
//
//        @Generated
//        public void setMenuFrontEndByUidMaximumSize(final Long menuFrontEndByUidMaximumSize) {
//            this.menuFrontEndByUidMaximumSize = menuFrontEndByUidMaximumSize;
//        }
//
//        @Generated
//        public void setMenuFrontEndByUidExpireAfterWriteMinutes(final Long menuFrontEndByUidExpireAfterWriteMinutes) {
//            this.menuFrontEndByUidExpireAfterWriteMinutes = menuFrontEndByUidExpireAfterWriteMinutes;
//        }
//
//        @Generated
//        public boolean equals(final Object o) {
//            if (o == this) {
//                return true;
//            } else if (!(o instanceof GuavaCache)) {
//                return false;
//            } else {
//                GuavaCache other = (GuavaCache)o;
//                if (!other.canEqual(this)) {
//                    return false;
//                } else {
//                    label59: {
//                        Object this$menuBackEndByJwtMaximumSize = this.getMenuBackEndByJwtMaximumSize();
//                        Object other$menuBackEndByJwtMaximumSize = other.getMenuBackEndByJwtMaximumSize();
//                        if (this$menuBackEndByJwtMaximumSize == null) {
//                            if (other$menuBackEndByJwtMaximumSize == null) {
//                                break label59;
//                            }
//                        } else if (this$menuBackEndByJwtMaximumSize.equals(other$menuBackEndByJwtMaximumSize)) {
//                            break label59;
//                        }
//
//                        return false;
//                    }
//
//                    Object this$menuBackEndByJwtExpireAfterWriteMinutes = this.getMenuBackEndByJwtExpireAfterWriteMinutes();
//                    Object other$menuBackEndByJwtExpireAfterWriteMinutes = other.getMenuBackEndByJwtExpireAfterWriteMinutes();
//                    if (this$menuBackEndByJwtExpireAfterWriteMinutes == null) {
//                        if (other$menuBackEndByJwtExpireAfterWriteMinutes != null) {
//                            return false;
//                        }
//                    } else if (!this$menuBackEndByJwtExpireAfterWriteMinutes.equals(other$menuBackEndByJwtExpireAfterWriteMinutes)) {
//                        return false;
//                    }
//
//                    Object this$menuFrontEndByUidMaximumSize = this.getMenuFrontEndByUidMaximumSize();
//                    Object other$menuFrontEndByUidMaximumSize = other.getMenuFrontEndByUidMaximumSize();
//                    if (this$menuFrontEndByUidMaximumSize == null) {
//                        if (other$menuFrontEndByUidMaximumSize != null) {
//                            return false;
//                        }
//                    } else if (!this$menuFrontEndByUidMaximumSize.equals(other$menuFrontEndByUidMaximumSize)) {
//                        return false;
//                    }
//
//                    Object this$menuFrontEndByUidExpireAfterWriteMinutes = this.getMenuFrontEndByUidExpireAfterWriteMinutes();
//                    Object other$menuFrontEndByUidExpireAfterWriteMinutes = other.getMenuFrontEndByUidExpireAfterWriteMinutes();
//                    if (this$menuFrontEndByUidExpireAfterWriteMinutes == null) {
//                        if (other$menuFrontEndByUidExpireAfterWriteMinutes != null) {
//                            return false;
//                        }
//                    } else if (!this$menuFrontEndByUidExpireAfterWriteMinutes.equals(other$menuFrontEndByUidExpireAfterWriteMinutes)) {
//                        return false;
//                    }
//
//                    return true;
//                }
//            }
//        }
//
//        @Generated
//        protected boolean canEqual(final Object other) {
//            return other instanceof GuavaCache;
//        }
//
//        @Generated
//        public int hashCode() {
//            int PRIME = true;
//            int result = 1;
//            Object $menuBackEndByJwtMaximumSize = this.getMenuBackEndByJwtMaximumSize();
//            result = result * 59 + ($menuBackEndByJwtMaximumSize == null ? 43 : $menuBackEndByJwtMaximumSize.hashCode());
//            Object $menuBackEndByJwtExpireAfterWriteMinutes = this.getMenuBackEndByJwtExpireAfterWriteMinutes();
//            result = result * 59 + ($menuBackEndByJwtExpireAfterWriteMinutes == null ? 43 : $menuBackEndByJwtExpireAfterWriteMinutes.hashCode());
//            Object $menuFrontEndByUidMaximumSize = this.getMenuFrontEndByUidMaximumSize();
//            result = result * 59 + ($menuFrontEndByUidMaximumSize == null ? 43 : $menuFrontEndByUidMaximumSize.hashCode());
//            Object $menuFrontEndByUidExpireAfterWriteMinutes = this.getMenuFrontEndByUidExpireAfterWriteMinutes();
//            result = result * 59 + ($menuFrontEndByUidExpireAfterWriteMinutes == null ? 43 : $menuFrontEndByUidExpireAfterWriteMinutes.hashCode());
//            return result;
//        }
//
//        @Generated
//        public String toString() {
//            return "ApplicationProperties.GuavaCache(menuBackEndByJwtMaximumSize=" + this.getMenuBackEndByJwtMaximumSize() + ", menuBackEndByJwtExpireAfterWriteMinutes=" + this.getMenuBackEndByJwtExpireAfterWriteMinutes() + ", menuFrontEndByUidMaximumSize=" + this.getMenuFrontEndByUidMaximumSize() + ", menuFrontEndByUidExpireAfterWriteMinutes=" + this.getMenuFrontEndByUidExpireAfterWriteMinutes() + ")";
//        }
//    }
//
//    public static class Restapi {
//        private int maxConnection;
//        private int maxPerRoute;
//        private int connectTimeoutInMs;
//        private int closeIdleConnectionsInMs;
//        private int connectionManagerShutdownInMs;
//        private String apiUrlDefault;
//        private String apiKeyDefault;
//        private String apiUrlAuth;
//        private String apiKeyAuth;
//
//        @Generated
//        public Restapi() {
//        }
//
//        @Generated
//        public int getMaxConnection() {
//            return this.maxConnection;
//        }
//
//        @Generated
//        public int getMaxPerRoute() {
//            return this.maxPerRoute;
//        }
//
//        @Generated
//        public int getConnectTimeoutInMs() {
//            return this.connectTimeoutInMs;
//        }
//
//        @Generated
//        public int getCloseIdleConnectionsInMs() {
//            return this.closeIdleConnectionsInMs;
//        }
//
//        @Generated
//        public int getConnectionManagerShutdownInMs() {
//            return this.connectionManagerShutdownInMs;
//        }
//
//        @Generated
//        public String getApiUrlDefault() {
//            return this.apiUrlDefault;
//        }
//
//        @Generated
//        public String getApiKeyDefault() {
//            return this.apiKeyDefault;
//        }
//
//        @Generated
//        public String getApiUrlAuth() {
//            return this.apiUrlAuth;
//        }
//
//        @Generated
//        public String getApiKeyAuth() {
//            return this.apiKeyAuth;
//        }
//
//        @Generated
//        public void setMaxConnection(final int maxConnection) {
//            this.maxConnection = maxConnection;
//        }
//
//        @Generated
//        public void setMaxPerRoute(final int maxPerRoute) {
//            this.maxPerRoute = maxPerRoute;
//        }
//
//        @Generated
//        public void setConnectTimeoutInMs(final int connectTimeoutInMs) {
//            this.connectTimeoutInMs = connectTimeoutInMs;
//        }
//
//        @Generated
//        public void setCloseIdleConnectionsInMs(final int closeIdleConnectionsInMs) {
//            this.closeIdleConnectionsInMs = closeIdleConnectionsInMs;
//        }
//
//        @Generated
//        public void setConnectionManagerShutdownInMs(final int connectionManagerShutdownInMs) {
//            this.connectionManagerShutdownInMs = connectionManagerShutdownInMs;
//        }
//
//        @Generated
//        public void setApiUrlDefault(final String apiUrlDefault) {
//            this.apiUrlDefault = apiUrlDefault;
//        }
//
//        @Generated
//        public void setApiKeyDefault(final String apiKeyDefault) {
//            this.apiKeyDefault = apiKeyDefault;
//        }
//
//        @Generated
//        public void setApiUrlAuth(final String apiUrlAuth) {
//            this.apiUrlAuth = apiUrlAuth;
//        }
//
//        @Generated
//        public void setApiKeyAuth(final String apiKeyAuth) {
//            this.apiKeyAuth = apiKeyAuth;
//        }
//
//        @Generated
//        public boolean equals(final Object o) {
//            if (o == this) {
//                return true;
//            } else if (!(o instanceof Restapi)) {
//                return false;
//            } else {
//                Restapi other = (Restapi)o;
//                if (!other.canEqual(this)) {
//                    return false;
//                } else if (this.getMaxConnection() != other.getMaxConnection()) {
//                    return false;
//                } else if (this.getMaxPerRoute() != other.getMaxPerRoute()) {
//                    return false;
//                } else if (this.getConnectTimeoutInMs() != other.getConnectTimeoutInMs()) {
//                    return false;
//                } else if (this.getCloseIdleConnectionsInMs() != other.getCloseIdleConnectionsInMs()) {
//                    return false;
//                } else if (this.getConnectionManagerShutdownInMs() != other.getConnectionManagerShutdownInMs()) {
//                    return false;
//                } else {
//                    label71: {
//                        Object this$apiUrlDefault = this.getApiUrlDefault();
//                        Object other$apiUrlDefault = other.getApiUrlDefault();
//                        if (this$apiUrlDefault == null) {
//                            if (other$apiUrlDefault == null) {
//                                break label71;
//                            }
//                        } else if (this$apiUrlDefault.equals(other$apiUrlDefault)) {
//                            break label71;
//                        }
//
//                        return false;
//                    }
//
//                    Object this$apiKeyDefault = this.getApiKeyDefault();
//                    Object other$apiKeyDefault = other.getApiKeyDefault();
//                    if (this$apiKeyDefault == null) {
//                        if (other$apiKeyDefault != null) {
//                            return false;
//                        }
//                    } else if (!this$apiKeyDefault.equals(other$apiKeyDefault)) {
//                        return false;
//                    }
//
//                    label57: {
//                        Object this$apiUrlAuth = this.getApiUrlAuth();
//                        Object other$apiUrlAuth = other.getApiUrlAuth();
//                        if (this$apiUrlAuth == null) {
//                            if (other$apiUrlAuth == null) {
//                                break label57;
//                            }
//                        } else if (this$apiUrlAuth.equals(other$apiUrlAuth)) {
//                            break label57;
//                        }
//
//                        return false;
//                    }
//
//                    Object this$apiKeyAuth = this.getApiKeyAuth();
//                    Object other$apiKeyAuth = other.getApiKeyAuth();
//                    if (this$apiKeyAuth == null) {
//                        if (other$apiKeyAuth != null) {
//                            return false;
//                        }
//                    } else if (!this$apiKeyAuth.equals(other$apiKeyAuth)) {
//                        return false;
//                    }
//
//                    return true;
//                }
//            }
//        }
//
//        @Generated
//        protected boolean canEqual(final Object other) {
//            return other instanceof Restapi;
//        }
//
//        @Generated
//        public int hashCode() {
//            int PRIME = true;
//            int result = 1;
//            result = result * 59 + this.getMaxConnection();
//            result = result * 59 + this.getMaxPerRoute();
//            result = result * 59 + this.getConnectTimeoutInMs();
//            result = result * 59 + this.getCloseIdleConnectionsInMs();
//            result = result * 59 + this.getConnectionManagerShutdownInMs();
//            Object $apiUrlDefault = this.getApiUrlDefault();
//            result = result * 59 + ($apiUrlDefault == null ? 43 : $apiUrlDefault.hashCode());
//            Object $apiKeyDefault = this.getApiKeyDefault();
//            result = result * 59 + ($apiKeyDefault == null ? 43 : $apiKeyDefault.hashCode());
//            Object $apiUrlAuth = this.getApiUrlAuth();
//            result = result * 59 + ($apiUrlAuth == null ? 43 : $apiUrlAuth.hashCode());
//            Object $apiKeyAuth = this.getApiKeyAuth();
//            result = result * 59 + ($apiKeyAuth == null ? 43 : $apiKeyAuth.hashCode());
//            return result;
//        }
//
//        @Generated
//        public String toString() {
//            return "ApplicationProperties.Restapi(maxConnection=" + this.getMaxConnection() + ", maxPerRoute=" + this.getMaxPerRoute() + ", connectTimeoutInMs=" + this.getConnectTimeoutInMs() + ", closeIdleConnectionsInMs=" + this.getCloseIdleConnectionsInMs() + ", connectionManagerShutdownInMs=" + this.getConnectionManagerShutdownInMs() + ", apiUrlDefault=" + this.getApiUrlDefault() + ", apiKeyDefault=" + this.getApiKeyDefault() + ", apiUrlAuth=" + this.getApiUrlAuth() + ", apiKeyAuth=" + this.getApiKeyAuth() + ")";
//        }
//    }
//
//    public static class Paging {
//        private String defaultPageNumber;
//        private String defaultPageSize;
//        private int maxPageSize;
//
//        @Generated
//        public Paging() {
//        }
//
//        @Generated
//        public String getDefaultPageNumber() {
//            return this.defaultPageNumber;
//        }
//
//        @Generated
//        public String getDefaultPageSize() {
//            return this.defaultPageSize;
//        }
//
//        @Generated
//        public int getMaxPageSize() {
//            return this.maxPageSize;
//        }
//
//        @Generated
//        public void setDefaultPageNumber(final String defaultPageNumber) {
//            this.defaultPageNumber = defaultPageNumber;
//        }
//
//        @Generated
//        public void setDefaultPageSize(final String defaultPageSize) {
//            this.defaultPageSize = defaultPageSize;
//        }
//
//        @Generated
//        public void setMaxPageSize(final int maxPageSize) {
//            this.maxPageSize = maxPageSize;
//        }
//
//        @Generated
//        public boolean equals(final Object o) {
//            if (o == this) {
//                return true;
//            } else if (!(o instanceof Paging)) {
//                return false;
//            } else {
//                Paging other = (Paging)o;
//                if (!other.canEqual(this)) {
//                    return false;
//                } else if (this.getMaxPageSize() != other.getMaxPageSize()) {
//                    return false;
//                } else {
//                    Object this$defaultPageNumber = this.getDefaultPageNumber();
//                    Object other$defaultPageNumber = other.getDefaultPageNumber();
//                    if (this$defaultPageNumber == null) {
//                        if (other$defaultPageNumber != null) {
//                            return false;
//                        }
//                    } else if (!this$defaultPageNumber.equals(other$defaultPageNumber)) {
//                        return false;
//                    }
//
//                    Object this$defaultPageSize = this.getDefaultPageSize();
//                    Object other$defaultPageSize = other.getDefaultPageSize();
//                    if (this$defaultPageSize == null) {
//                        if (other$defaultPageSize != null) {
//                            return false;
//                        }
//                    } else if (!this$defaultPageSize.equals(other$defaultPageSize)) {
//                        return false;
//                    }
//
//                    return true;
//                }
//            }
//        }
//
//        @Generated
//        protected boolean canEqual(final Object other) {
//            return other instanceof Paging;
//        }
//
//        @Generated
//        public int hashCode() {
//            int PRIME = true;
//            int result = 1;
//            result = result * 59 + this.getMaxPageSize();
//            Object $defaultPageNumber = this.getDefaultPageNumber();
//            result = result * 59 + ($defaultPageNumber == null ? 43 : $defaultPageNumber.hashCode());
//            Object $defaultPageSize = this.getDefaultPageSize();
//            result = result * 59 + ($defaultPageSize == null ? 43 : $defaultPageSize.hashCode());
//            return result;
//        }
//
//        @Generated
//        public String toString() {
//            return "ApplicationProperties.Paging(defaultPageNumber=" + this.getDefaultPageNumber() + ", defaultPageSize=" + this.getDefaultPageSize() + ", maxPageSize=" + this.getMaxPageSize() + ")";
//        }
//    }
//
//    public static class CurrentApp {
//        private String appCode;
//        private String appName;
//        private String tableNamingStrategyPrefix;
//        private String tableNamingStrategyPrefixIgnore;
//        private String logApiRequestMenuCode;
//        private String logApiResponseMenuCode;
//        private String checkApiKeyForExternalApi;
//        private Boolean checkDeviceOfJwt;
//        private String apiKey;
//        private int jwtExpirationInMs;
//
//        @Generated
//        public CurrentApp() {
//        }
//
//        @Generated
//        public String getAppCode() {
//            return this.appCode;
//        }
//
//        @Generated
//        public String getAppName() {
//            return this.appName;
//        }
//
//        @Generated
//        public String getTableNamingStrategyPrefix() {
//            return this.tableNamingStrategyPrefix;
//        }
//
//        @Generated
//        public String getTableNamingStrategyPrefixIgnore() {
//            return this.tableNamingStrategyPrefixIgnore;
//        }
//
//        @Generated
//        public String getLogApiRequestMenuCode() {
//            return this.logApiRequestMenuCode;
//        }
//
//        @Generated
//        public String getLogApiResponseMenuCode() {
//            return this.logApiResponseMenuCode;
//        }
//
//        @Generated
//        public String getCheckApiKeyForExternalApi() {
//            return this.checkApiKeyForExternalApi;
//        }
//
//        @Generated
//        public Boolean getCheckDeviceOfJwt() {
//            return this.checkDeviceOfJwt;
//        }
//
//        @Generated
//        public String getApiKey() {
//            return this.apiKey;
//        }
//
//        @Generated
//        public int getJwtExpirationInMs() {
//            return this.jwtExpirationInMs;
//        }
//
//        @Generated
//        public void setAppCode(final String appCode) {
//            this.appCode = appCode;
//        }
//
//        @Generated
//        public void setAppName(final String appName) {
//            this.appName = appName;
//        }
//
//        @Generated
//        public void setTableNamingStrategyPrefix(final String tableNamingStrategyPrefix) {
//            this.tableNamingStrategyPrefix = tableNamingStrategyPrefix;
//        }
//
//        @Generated
//        public void setTableNamingStrategyPrefixIgnore(final String tableNamingStrategyPrefixIgnore) {
//            this.tableNamingStrategyPrefixIgnore = tableNamingStrategyPrefixIgnore;
//        }
//
//        @Generated
//        public void setLogApiRequestMenuCode(final String logApiRequestMenuCode) {
//            this.logApiRequestMenuCode = logApiRequestMenuCode;
//        }
//
//        @Generated
//        public void setLogApiResponseMenuCode(final String logApiResponseMenuCode) {
//            this.logApiResponseMenuCode = logApiResponseMenuCode;
//        }
//
//        @Generated
//        public void setCheckApiKeyForExternalApi(final String checkApiKeyForExternalApi) {
//            this.checkApiKeyForExternalApi = checkApiKeyForExternalApi;
//        }
//
//        @Generated
//        public void setCheckDeviceOfJwt(final Boolean checkDeviceOfJwt) {
//            this.checkDeviceOfJwt = checkDeviceOfJwt;
//        }
//
//        @Generated
//        public void setApiKey(final String apiKey) {
//            this.apiKey = apiKey;
//        }
//
//        @Generated
//        public void setJwtExpirationInMs(final int jwtExpirationInMs) {
//            this.jwtExpirationInMs = jwtExpirationInMs;
//        }
//
//        @Generated
//        public boolean equals(final Object o) {
//            if (o == this) {
//                return true;
//            } else if (!(o instanceof CurrentApp)) {
//                return false;
//            } else {
//                CurrentApp other = (CurrentApp)o;
//                if (!other.canEqual(this)) {
//                    return false;
//                } else if (this.getJwtExpirationInMs() != other.getJwtExpirationInMs()) {
//                    return false;
//                } else {
//                    label121: {
//                        Object this$checkDeviceOfJwt = this.getCheckDeviceOfJwt();
//                        Object other$checkDeviceOfJwt = other.getCheckDeviceOfJwt();
//                        if (this$checkDeviceOfJwt == null) {
//                            if (other$checkDeviceOfJwt == null) {
//                                break label121;
//                            }
//                        } else if (this$checkDeviceOfJwt.equals(other$checkDeviceOfJwt)) {
//                            break label121;
//                        }
//
//                        return false;
//                    }
//
//                    Object this$appCode = this.getAppCode();
//                    Object other$appCode = other.getAppCode();
//                    if (this$appCode == null) {
//                        if (other$appCode != null) {
//                            return false;
//                        }
//                    } else if (!this$appCode.equals(other$appCode)) {
//                        return false;
//                    }
//
//                    label107: {
//                        Object this$appName = this.getAppName();
//                        Object other$appName = other.getAppName();
//                        if (this$appName == null) {
//                            if (other$appName == null) {
//                                break label107;
//                            }
//                        } else if (this$appName.equals(other$appName)) {
//                            break label107;
//                        }
//
//                        return false;
//                    }
//
//                    Object this$tableNamingStrategyPrefix = this.getTableNamingStrategyPrefix();
//                    Object other$tableNamingStrategyPrefix = other.getTableNamingStrategyPrefix();
//                    if (this$tableNamingStrategyPrefix == null) {
//                        if (other$tableNamingStrategyPrefix != null) {
//                            return false;
//                        }
//                    } else if (!this$tableNamingStrategyPrefix.equals(other$tableNamingStrategyPrefix)) {
//                        return false;
//                    }
//
//                    Object this$tableNamingStrategyPrefixIgnore = this.getTableNamingStrategyPrefixIgnore();
//                    Object other$tableNamingStrategyPrefixIgnore = other.getTableNamingStrategyPrefixIgnore();
//                    if (this$tableNamingStrategyPrefixIgnore == null) {
//                        if (other$tableNamingStrategyPrefixIgnore != null) {
//                            return false;
//                        }
//                    } else if (!this$tableNamingStrategyPrefixIgnore.equals(other$tableNamingStrategyPrefixIgnore)) {
//                        return false;
//                    }
//
//                    label86: {
//                        Object this$logApiRequestMenuCode = this.getLogApiRequestMenuCode();
//                        Object other$logApiRequestMenuCode = other.getLogApiRequestMenuCode();
//                        if (this$logApiRequestMenuCode == null) {
//                            if (other$logApiRequestMenuCode == null) {
//                                break label86;
//                            }
//                        } else if (this$logApiRequestMenuCode.equals(other$logApiRequestMenuCode)) {
//                            break label86;
//                        }
//
//                        return false;
//                    }
//
//                    label79: {
//                        Object this$logApiResponseMenuCode = this.getLogApiResponseMenuCode();
//                        Object other$logApiResponseMenuCode = other.getLogApiResponseMenuCode();
//                        if (this$logApiResponseMenuCode == null) {
//                            if (other$logApiResponseMenuCode == null) {
//                                break label79;
//                            }
//                        } else if (this$logApiResponseMenuCode.equals(other$logApiResponseMenuCode)) {
//                            break label79;
//                        }
//
//                        return false;
//                    }
//
//                    Object this$checkApiKeyForExternalApi = this.getCheckApiKeyForExternalApi();
//                    Object other$checkApiKeyForExternalApi = other.getCheckApiKeyForExternalApi();
//                    if (this$checkApiKeyForExternalApi == null) {
//                        if (other$checkApiKeyForExternalApi != null) {
//                            return false;
//                        }
//                    } else if (!this$checkApiKeyForExternalApi.equals(other$checkApiKeyForExternalApi)) {
//                        return false;
//                    }
//
//                    Object this$apiKey = this.getApiKey();
//                    Object other$apiKey = other.getApiKey();
//                    if (this$apiKey == null) {
//                        if (other$apiKey != null) {
//                            return false;
//                        }
//                    } else if (!this$apiKey.equals(other$apiKey)) {
//                        return false;
//                    }
//
//                    return true;
//                }
//            }
//        }
//
//        @Generated
//        protected boolean canEqual(final Object other) {
//            return other instanceof CurrentApp;
//        }
//
//        @Generated
//        public int hashCode() {
//            int PRIME = true;
//            int result = 1;
//            result = result * 59 + this.getJwtExpirationInMs();
//            Object $checkDeviceOfJwt = this.getCheckDeviceOfJwt();
//            result = result * 59 + ($checkDeviceOfJwt == null ? 43 : $checkDeviceOfJwt.hashCode());
//            Object $appCode = this.getAppCode();
//            result = result * 59 + ($appCode == null ? 43 : $appCode.hashCode());
//            Object $appName = this.getAppName();
//            result = result * 59 + ($appName == null ? 43 : $appName.hashCode());
//            Object $tableNamingStrategyPrefix = this.getTableNamingStrategyPrefix();
//            result = result * 59 + ($tableNamingStrategyPrefix == null ? 43 : $tableNamingStrategyPrefix.hashCode());
//            Object $tableNamingStrategyPrefixIgnore = this.getTableNamingStrategyPrefixIgnore();
//            result = result * 59 + ($tableNamingStrategyPrefixIgnore == null ? 43 : $tableNamingStrategyPrefixIgnore.hashCode());
//            Object $logApiRequestMenuCode = this.getLogApiRequestMenuCode();
//            result = result * 59 + ($logApiRequestMenuCode == null ? 43 : $logApiRequestMenuCode.hashCode());
//            Object $logApiResponseMenuCode = this.getLogApiResponseMenuCode();
//            result = result * 59 + ($logApiResponseMenuCode == null ? 43 : $logApiResponseMenuCode.hashCode());
//            Object $checkApiKeyForExternalApi = this.getCheckApiKeyForExternalApi();
//            result = result * 59 + ($checkApiKeyForExternalApi == null ? 43 : $checkApiKeyForExternalApi.hashCode());
//            Object $apiKey = this.getApiKey();
//            result = result * 59 + ($apiKey == null ? 43 : $apiKey.hashCode());
//            return result;
//        }
//
//        @Generated
//        public String toString() {
//            return "ApplicationProperties.CurrentApp(appCode=" + this.getAppCode() + ", appName=" + this.getAppName() + ", tableNamingStrategyPrefix=" + this.getTableNamingStrategyPrefix() + ", tableNamingStrategyPrefixIgnore=" + this.getTableNamingStrategyPrefixIgnore() + ", logApiRequestMenuCode=" + this.getLogApiRequestMenuCode() + ", logApiResponseMenuCode=" + this.getLogApiResponseMenuCode() + ", checkApiKeyForExternalApi=" + this.getCheckApiKeyForExternalApi() + ", checkDeviceOfJwt=" + this.getCheckDeviceOfJwt() + ", apiKey=" + this.getApiKey() + ", jwtExpirationInMs=" + this.getJwtExpirationInMs() + ")";
//        }
//    }
//}
