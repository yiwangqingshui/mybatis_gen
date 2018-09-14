package com.yiwangqingshui.mybatis.gen.model.java;


import com.google.common.collect.Sets;

import java.util.Set;

/**
 *
 * @author smc
 * @date 2018-09-10 21:11
 * @since
 **/
public class Base {

    private String classPath;

    private Set<String> importLists = Sets.newHashSet();

    private String className;

    private String desc;

    private String packageName;

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Set<String> getImportLists() {
        return importLists;
    }

    public void setImportLists(Set<String> importLists) {
        this.importLists = importLists;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void addImportLists(String importStr){
        importLists.add(importStr);
    }

    public void addAllImportList(Set<String> sourceIimportLists){
        this.importLists.addAll(sourceIimportLists);
    }
}
