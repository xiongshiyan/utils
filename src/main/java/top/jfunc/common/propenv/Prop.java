package top.jfunc.common.propenv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 封装Properties，其构造方法中就有EnvStream的加载逻辑
 * @author 熊诗言
 * @see BaseEnvStream#loadEnvInputStream(String)
 */
public class Prop{
        private Properties properties;

        public Prop(String fileName) {
            this(fileName, "UTF-8");
        }

        public Prop(String fileName, String encoding) {
            BaseEnvStream envProp = EnvStreamFactory.getEnvStream(EnvStreamFactory.ENV_STREAM_KIND);
            try(InputStream stream = envProp.loadEnvInputStream(fileName)) {
                this.properties = null;
                this.properties = new Properties();
                InputStreamReader   reader = new InputStreamReader(stream, encoding);
                this.properties.load(reader);
                reader.close();
            } catch (IOException var12) {
                throw new RuntimeException("Error loading properties file.", var12);
            }

        }

        public String get(String key) {
            return this.properties.getProperty(key);
        }

        public String get(String key, String defaultValue) {
            return this.properties.getProperty(key, defaultValue);
        }

        public Integer getInt(String key) {
            return this.getInt(key, (Integer)null);
        }

        public Integer getInt(String key, Integer defaultValue) {
            String value = this.properties.getProperty(key);
            return value != null ? Integer.parseInt(value) : defaultValue;
        }

        public Long getLong(String key) {
            return this.getLong(key, (Long)null);
        }

        public Long getLong(String key, Long defaultValue) {
            String value = this.properties.getProperty(key);
            return value != null ? Long.parseLong(value) : defaultValue;
        }

        public Boolean getBoolean(String key) {
            return this.getBoolean(key, (Boolean)null);
        }

        public Boolean getBoolean(String key, Boolean defaultValue) {
            String value = this.properties.getProperty(key);
            return value != null ? Boolean.parseBoolean(value) : defaultValue;
        }

        public boolean containsKey(String key) {
            return this.properties.containsKey(key);
        }

        public Properties getProperties() {
            return this.properties;
        }

        public Enumeration<Object> keys(){
            return this.properties.keys();
        }

        @Override
        public String toString() {
            return this.properties.toString();
        }
    }