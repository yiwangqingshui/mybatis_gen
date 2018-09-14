package com.yiwangqingshui.mybatis.gen;

import com.google.common.collect.Lists;
import com.yiwangqingshui.mybatis.gen.dataloaders.GenAbstractDataLoader;
import com.yiwangqingshui.mybatis.gen.model.db.SupportDB;
import com.yiwangqingshui.mybatis.gen.model.java.DAO;
import com.yiwangqingshui.mybatis.gen.utils.CmdUtil;
import com.yiwangqingshui.mybatis.gen.utils.ConfigUtil;
import com.yiwangqingshui.mybatis.gen.utils.TemplateFileUtils;
import fmpp.ProcessingException;
import fmpp.setting.SettingException;
import fmpp.setting.Settings;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;


/**
 * maven插件启动类,整个启动实例化配置文件入口
 *
 * @author smc
 * @date 2018-09-10 14:04
 * @since 1.0.0
 */
@Mojo(name = "gen")
public class MybatisGenMojo extends AbstractMojo {

    private CmdUtil cmdUtil = new CmdUtil();

    private static final String TABLE_SEP = ";";

    /**
     * 停止执行标识
     */
    private static final String STOP_RUN_FLAG = "q";


    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    /**
     * 代码生成目录
     * 默认为当前项目下面
     */
    @Parameter(defaultValue = "${project.basedir}")
    private File outputDirectory;

    /**
     * 默认的系统模板文件
     */
    @Parameter(defaultValue = "dalgen/templates/")
    private File templateDirectory;

    @Parameter(defaultValue = "com.yiwangqingshui.dal.xxx")
    private String packageName;

    /**
     * 默认去掉表明t_前缀的内容
     */
    @Parameter(defaultValue = "t_")
    private String tablePrefix;

    /**
     * 是否需要分页标识
     * 默认创建的语句都是需要分页
     */
    @Parameter(defaultValue = "yes")
    private String pageFlag;

    /**
     * 数据库类型
     * 默认mysql
     */
    @Parameter(defaultValue = "mysql")
    private String dbType;

    @Parameter(defaultValue = "org.gjt.mm.mysql.Driver")
    private String driverClass;

    @Parameter(defaultValue = "jdbc:mysql://127.0.0.1:3306/config")
    private String url;

    @Parameter(defaultValue = "root")
    private String username;

    @Parameter(defaultValue = "123456")
    private String password;

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public File getTemplateDirectory() {
        return templateDirectory;
    }

    public void setTemplateDirectory(File templateDirectory) {
        this.templateDirectory = templateDirectory;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag) {
        this.pageFlag = pageFlag;
    }

    private void print() {
        System.out.println("***********************************************************\n" +
                           "**           欢迎使用一汪清水mybatis代码生成器           **\n" +
                           "**                 一汪清水值得拥有                      **\n" +
                           "**             https://www.yiwangqingshui.com            **\n" +
                           "**              有bug请联系：sunmch@163.com              **\n" +
                           "***********************************************************");
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        print();
        if (StringUtils.isBlank(SupportDB.getName(dbType.toLowerCase()))) {
            this.getLog().error("目前插件只支持[mysql,oracle]两种数据库，暂时不支持"+dbType +"数据库，对不起: 您的操作将停止执行");
            System.exit(0);
        }
        System.out.println("\n\n\n");
        this.getLog().info("==========开始执行maven插件=========");
        TemplateFileUtils.copyFile(this);
        String cmd = cmdUtil.consoleInput();
        if (STOP_RUN_FLAG.equalsIgnoreCase(cmd)) {
            this.getLog().info("您停止了代码自动生成操作");
            System.exit(0);
        }
        ConfigUtil.setMojo(this);
        ConfigUtil.setPackageName(this.getPackageName());
        try {
            for (String tableName : cmd.split(TABLE_SEP)) {
                ConfigUtil.setCmd(tableName.toUpperCase());
                executeGen(tableName.toUpperCase());
            }
        } catch (SettingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始生成文件
     */
    public void executeGen(String cmdName) throws SettingException {
        Settings settings = new Settings(new File("."));
        settings.set(Settings.NAME_SOURCE_ROOT, templateDirectory.getAbsolutePath());
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        String outPath = outputDirectory.getAbsolutePath();
        settings.set(Settings.NAME_OUTPUT_ROOT, outPath);
        settings.set(Settings.NAME_OUTPUT_ENCODING, "UTF-8");
        settings.set(Settings.NAME_SOURCE_ENCODING, "UTF-8");
        settings.set(Settings.NAME_DATA, "gen:" + GenAbstractDataLoader.class.getName() + "()");
        try {
            settings.execute();
        } catch (ProcessingException e) {
            e.printStackTrace();
        }

    }


}
