package top.jfunc.common.propenv;
/**
 * 本包是用于加载properties配置文件的，具备通过环境变量来找到相应配置的功能，环境变量的key为 ENVSETTING ，
 * 加载的顺序为根据环境变量env加载文件(-DENVSETTING=test)，首先寻找${fileName}-${env}.ext，没找到就寻找找${env}/${fileName}.ext，再没找到就找找${env}/${fileName}.ext。
 * 没有设置env环境变量的时候，给什么路径就从什么路径找，此由EnvStream抽象类利用模板方法模式实现。
 * 本包提供了两种加载路径，一种是classpath（admin），一种是文件系统（rest、runner等，配置文件外部化）。分别由ClasspathEnvStream和FileEnvStream实现，此在Prop的初始化中利用静态工厂模式实现
 * BaseEnvStream envProp = EnvStreamFactory.getEnvStream(EnvStreamFactory.ENV_STREAM_KIND)。
 * 使用方式：
 * 1.确保程序启动前已经设置了环境变量ENVSETTING=test/dev/prod/pretest等
 * 2.明确加载的方式，调用EnvStreamFactory.choose()方法。如果是类路径下加载的话，不用做任何设置，系统默认。
 * 3.PropertiesUtils.use(fileName).getProp()来加载配置文件
 */