package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wallance on 4/23/17.
 */
@Entity
@Table(name = "sys_dictionary_table", schema = "zx120_test")
public class DictionaryModel {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "parameter_english_name")
    private String paramEng;

    @Column(name = "parameter_chinese_name")
    private String paramChs;

    @Column(name = "english_value")
    private String paramValueEng;

    @Column(name = "chinese_value")
    private String paramValueChs;

    @Column(name = "int_value")
    private int code;

    @Column(name = "home_table")
    private String homeTable;

    @Column(name = "parameter_desc")
    private String paramDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParamEng() {
        return paramEng;
    }

    public void setParamEng(String paramEng) {
        this.paramEng = paramEng;
    }

    public String getParamChs() {
        return paramChs;
    }

    public void setParamChs(String paramChs) {
        this.paramChs = paramChs;
    }

    public String getParamValueEng() {
        return paramValueEng;
    }

    public void setParamValueEng(String paramValueEng) {
        this.paramValueEng = paramValueEng;
    }

    public String getParamValueChs() {
        return paramValueChs;
    }

    public void setParamValueChs(String paramValueChs) {
        this.paramValueChs = paramValueChs;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHomeTable() {
        return homeTable;
    }

    public void setHomeTable(String homeTable) {
        this.homeTable = homeTable;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryModel that = (DictionaryModel) o;

        if (id != that.id) return false;
        if (code != that.code) return false;
        if (!paramEng.equals(that.paramEng)) return false;
        if (!paramChs.equals(that.paramChs)) return false;
        if (!paramValueEng.equals(that.paramValueEng)) return false;
        if (!paramValueChs.equals(that.paramValueChs)) return false;
        if (!homeTable.equals(that.homeTable)) return false;
        return paramDesc.equals(that.paramDesc);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + paramEng.hashCode();
        result = 31 * result + paramChs.hashCode();
        result = 31 * result + paramValueEng.hashCode();
        result = 31 * result + paramValueChs.hashCode();
        result = 31 * result + code;
        result = 31 * result + homeTable.hashCode();
        result = 31 * result + paramDesc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DictionaryModel{" +
                "id=" + id +
                ", paramEng='" + paramEng + '\'' +
                ", paramChs='" + paramChs + '\'' +
                ", paramValueEng='" + paramValueEng + '\'' +
                ", paramValueChs='" + paramValueChs + '\'' +
                ", code=" + code +
                ", homeTable='" + homeTable + '\'' +
                ", paramDesc='" + paramDesc + '\'' +
                '}';
    }

}
