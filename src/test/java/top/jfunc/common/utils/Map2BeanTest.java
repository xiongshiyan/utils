package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author xiongshiyan at 2018/5/18
 */
public class Map2BeanTest {

    @Test
    public void testMap2Bean(){
        Map<String , Object> map = new HashMap<>();
        map.put("name" , "xiongshiyan");
        map.put("age" , 20);
        map.put("age2" , Integer.valueOf(20));
        map.put("age3" , BigInteger.valueOf(20));
        map.put("byte1" , 20);
        map.put("short1" , (short)20);
        map.put("float1" , 20F);
        map.put("double1" , 20D);
        map.put("bigDecimal" , 20);
        map.put("bigInteger" , new BigInteger("20"));
        map.put("salary" , 1L);
        map.put("salary_year" , 10000);
        map.put("ade" , "dsads");
        map.put("s" , "s");
        //map.put("ss" , "12");
        // map.put("dd" , 32);
        JavaBean1 convert1 = Map2Bean.convert(map, JavaBean1.class);

        //Assert.assertThat(convert1.getS() , equalTo("s"));
        Assert.assertThat(convert1.getName() , equalTo("xiongshiyan"));
        Assert.assertThat(convert1.getAge() , equalTo(20));
        Assert.assertThat(convert1.getAge2() , equalTo(20));
        Assert.assertThat(convert1.getAge3() , equalTo(20));
        Assert.assertThat(convert1.getByte1() , equalTo((byte)20));
        Assert.assertThat(convert1.getShort1() , equalTo((short)20));
        Assert.assertThat(convert1.getFloat1() , equalTo(20F));
        Assert.assertThat(convert1.getDouble1() , equalTo(20D));
        Assert.assertThat(convert1.getBigDecimal() , equalTo(BigDecimal.valueOf(20)));
        Assert.assertThat(convert1.getBigInteger() , equalTo(BigInteger.valueOf(20)));
        Assert.assertThat(convert1.getJavaBean2().getSalary(), equalTo(1L));
        Assert.assertThat(convert1.getJavaBean2().getSalary_year(), equalTo("10000"));
        Assert.assertThat(convert1.getJavaBean3().getAde(), equalTo("dsads"));
        Assert.assertThat(convert1.getJavaBean3().getSalary(), equalTo("1"));
        Assert.assertThat(convert1.getJavaBean3().getJavaBean4().getDd(), equalTo(0));
        Assert.assertThat(convert1.getJavaBean3().getJavaBean4().getSs(), equalTo(null));
    }

    @Test
    public void testMap2BeanInherited(){
        Map<String , Object> map = new HashMap<>();
        map.put("ss" , Integer.valueOf(20));
        map.put("dd" , 20);
        map.put("aa" , "xiongshiyan");
        JavaBean5 convert = Map2Bean.convert(map, JavaBean5.class);
        Assert.assertThat(convert.getAa() , equalTo("xiongshiyan"));
        Assert.assertThat(convert.getSs() , equalTo(20));
        Assert.assertThat(convert.getDd() , equalTo(20));
    }
    public static class JavaBean1{
        private static String s;
        private String name;
        private Integer age;
        private int age2;
        private int age3;
        private byte byte1;
        private Short short1;
        private Float float1;
        private Double double1;
        private BigDecimal bigDecimal;
        private BigInteger bigInteger;
        private JavaBean2 javaBean2;
        private JavaBean3 javaBean3;

        private List list;
        private Map map;
        private Set set;
        private String[] array;

        public String[] getArray() {
            return array;
        }

        public void setArray(String[] array) {
            this.array = array;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        public Set getSet() {
            return set;
        }

        public void setSet(Set set) {
            this.set = set;
        }

        public static String getS() {
            return s;
        }

        public static void setS(String s) {
            JavaBean1.s = s;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public int getAge2() {
            return age2;
        }

        public void setAge2(int age2) {
            this.age2 = age2;
        }

        public int getAge3() {
            return age3;
        }

        public void setAge3(int age3) {
            this.age3 = age3;
        }

        public JavaBean2 getJavaBean2() {
            return javaBean2;
        }

        public void setJavaBean2(JavaBean2 javaBean2) {
            this.javaBean2 = javaBean2;
        }


        public JavaBean3 getJavaBean3() {
            return javaBean3;
        }

        public void setJavaBean3(JavaBean3 javaBean3) {
            this.javaBean3 = javaBean3;
        }

        public byte getByte1() {
            return byte1;
        }

        public void setByte1(byte byte1) {
            this.byte1 = byte1;
        }

        public Short getShort1() {
            return short1;
        }

        public void setShort1(Short short1) {
            this.short1 = short1;
        }

        public Float getFloat1() {
            return float1;
        }

        public void setFloat1(Float float1) {
            this.float1 = float1;
        }

        public Double getDouble1() {
            return double1;
        }

        public void setDouble1(Double double1) {
            this.double1 = double1;
        }

        public BigDecimal getBigDecimal() {
            return bigDecimal;
        }

        public void setBigDecimal(BigDecimal bigDecimal) {
            this.bigDecimal = bigDecimal;
        }

        public BigInteger getBigInteger() {
            return bigInteger;
        }

        public void setBigInteger(BigInteger bigInteger) {
            this.bigInteger = bigInteger;
        }

        @Override
        public String toString() {
            return "JavaBean1{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", age2=" + age2 +
                    ", age3=" + age3 +
                    ", byte1=" + byte1 +
                    ", short1=" + short1 +
                    ", float1=" + float1 +
                    ", double1=" + double1 +
                    ", bigDecimal=" + bigDecimal +
                    ", bigInteger=" + bigInteger +
                    ", javaBean2=" + javaBean2 +
                    ", javaBean3=" + javaBean3 +
                    '}';
        }
    }
    public static class JavaBean2{
        private Long salary;
        private String salary_year;

        public String getSalary_year() {
            return salary_year;
        }

        public void setSalary_year(String salary_year) {
            this.salary_year = salary_year;
        }

        public Long getSalary() {
            return salary;
        }

        public void setSalary(Long salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "JavaBean2{" +
                    "salary=" + salary +
                    ", salary_year='" + salary_year + '\'' +
                    '}';
        }
    }
    public static class JavaBean3{
        private String ade;
        private String salary;
        private JavaBean4 javaBean4;

        public JavaBean4 getJavaBean4() {
            return javaBean4;
        }

        public void setJavaBean4(JavaBean4 javaBean4) {
            this.javaBean4 = javaBean4;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getAde() {
            return ade;
        }

        public void setAde(String ade) {
            this.ade = ade;
        }

        @Override
        public String toString() {
            return "JavaBean3{" +
                    "ade='" + ade + '\'' +
                    ", salary='" + salary + '\'' +
                    ", javaBean4=" + javaBean4 +
                    '}';
        }
    }
    public static class JavaBean4{
        private Integer ss;
        private int dd;

        public Integer getSs() {
            return ss;
        }

        public void setSs(Integer ss) {
            this.ss = ss;
        }

        public int getDd() {
            return dd;
        }

        public void setDd(int dd) {
            this.dd = dd;
        }

        @Override
        public String toString() {
            return "JavaBean4{" +
                    "ss=" + ss +
                    ", dd=" + dd +
                    '}';
        }
    }

    public static class JavaBean5 extends JavaBean4{
        private String aa;

        public String getAa() {
            return aa;
        }

        public void setAa(String aa) {
            this.aa = aa;
        }

        @Override
        public String toString() {
            return "JavaBean5{" +
                    "ss=" + getSs() +
                    ", dd=" + getDd() +
                    ", aa='" + aa + '\'' +
                    '}';
        }
    }
    @Test
    public void testClass(){
        Assert.assertTrue(Number.class.isAssignableFrom(Integer.class));
        Assert.assertTrue(Integer.class.isAssignableFrom(Integer.class));
        Assert.assertFalse(Number.class.isAssignableFrom(String.class));
        Assert.assertFalse(Number.class.isAssignableFrom(int.class));
    }

    @Test
    public void testCompatible(){
        Integer value = 23;
        Class clazz = BigInteger.class;
        Object o = BeanUtil.compatibleValue(value, clazz);
        System.out.println(o instanceof BigInteger);
    }
}
