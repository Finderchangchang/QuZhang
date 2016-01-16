package liuliu.quzhang.model;

import java.io.Serializable;

/**
 * 印章信息模型
 * Created by Administrator on 2016/1/16.
 */
public class YZXXModel implements Serializable {
    private int id;
    private String SignetId;//印章编码
    private boolean SPState;//审批状态
    private String GGId;//印章规格
    private String ZTCL;//章体材料
    private String YZType;//印章类型
    private String YZContent;//印章内容
    private String JBName;//经办人姓名
    private String UserCompanyName;//使用单位
    private String KZUnit;//刻制单位
    private String DJTime;//登记时间
    private boolean QUZhang;//是否已经取走（true,取走。false,未取走）

    public YZXXModel() {
    }

    public YZXXModel(String signetId, boolean SPState, String GGId, String ZTCL, String YZType, String YZContent, String JBName, String userCompanyName, String KZUnit, String DJTime, boolean QuZhang) {
        SignetId = signetId;
        this.SPState = SPState;
        this.GGId = GGId;
        this.ZTCL = ZTCL;
        this.YZType = YZType;
        this.YZContent = YZContent;
        this.JBName = JBName;
        UserCompanyName = userCompanyName;
        this.KZUnit = KZUnit;
        this.DJTime = DJTime;
        this.QUZhang = QuZhang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isQUZhang() {
        return QUZhang;
    }

    public void setQUZhang(boolean QUZhang) {
        this.QUZhang = QUZhang;
    }

    public String getSignetId() {
        return SignetId;
    }

    public void setSignetId(String signetId) {
        SignetId = signetId;
    }

    public boolean isSPState() {
        return SPState;
    }

    public void setSPState(boolean SPState) {
        this.SPState = SPState;
    }

    public String getGGId() {
        return GGId;
    }

    public void setGGId(String GGId) {
        this.GGId = GGId;
    }

    public String getZTCL() {
        return ZTCL;
    }

    public void setZTCL(String ZTCL) {
        this.ZTCL = ZTCL;
    }

    public String getYZType() {
        return YZType;
    }

    public void setYZType(String YZType) {
        this.YZType = YZType;
    }

    public String getYZContent() {
        return YZContent;
    }

    public void setYZContent(String YZContent) {
        this.YZContent = YZContent;
    }

    public String getJBName() {
        return JBName;
    }

    public void setJBName(String JBName) {
        this.JBName = JBName;
    }

    public String getUserCompanyName() {
        return UserCompanyName;
    }

    public void setUserCompanyName(String userCompanyName) {
        UserCompanyName = userCompanyName;
    }

    public String getKZUnit() {
        return KZUnit;
    }

    public void setKZUnit(String KZUnit) {
        this.KZUnit = KZUnit;
    }

    public String getDJTime() {
        return DJTime;
    }

    public void setDJTime(String DJTime) {
        this.DJTime = DJTime;
    }
}
