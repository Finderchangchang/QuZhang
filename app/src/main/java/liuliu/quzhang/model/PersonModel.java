package liuliu.quzhang.model;

import java.io.Serializable;

import liuliu.quzhang.base.Utils;


/**
 * Created by Administrator on 2015/11/11.
 */
public class PersonModel implements Serializable {
    private String id;
    private String PersonId;//人员编码
    private String PersonCompanyId;//企业编码
    private String PersonPoliceId;//民警编码
    private String PersonTerminalId;//设备编码
    private String PersonTransactionId;//事务编码
    private String PersonComapnyType;//人员隶属企业类型
    private String PersonComapnyName;//企业名称
    private String PersonCardType;//证件类型
    private String PersonCardId;//证件号码
    private String PersonName;//人员姓名
    private String PersonSex;//性别1男2女
    private String PersonNation;//民族
    private String PersonNative;//籍贯
    private String PersonBirthday;//出生日期
    private String PersonAddress;//详细地址
    private String PersonPhoneNumeber;//联系电话
    /// <summary>
    /// 东经。
    /// </summary>
    private String PersonEastPoint;

    /// <summary>
    /// 北纬。
    /// </summary>
    private String PersonNorthPoint;

    /// <summary>
    /// 证件照片。
    /// </summary>
    private String PersonCardImage;

    /// <summary>
    /// 实际照片。
    /// </summary>
    private String PersonRealImage;

    /// <summary>
    /// 备注（长度200）。
    /// </summary>
    private String PersonComment;

    /// <summary>
    /// 人员状态。
    /// </summary>
    private String PersonStatus;

    /// <summary>
    /// 创建时间。
    /// </summary>
    private String PersonCreateTime;

    /// <summary>
    /// 修改时间。
    /// </summary>
    private String PersonChangeTime;
    /// <summary>
    /// 日期。
    /// </summary>
    private String PersonDate;

    /// <summary>
    /// 流水序号。
    /// </summary>
    private int PersonIndex;

    private String PersonUpload;

    private String PersonAtLarge;
    /// <summary>
    /// 起租时间。
    /// </summary>
    private String CheckInTime;

    /// <summary>
    /// 截至时间。
    /// </summary>
    private String CheckOutTime;

    /// <summary>
    /// 是否由系统自动离店。
    /// </summary>
    private String AutoCheckOut;
    //company
    /// <summary>
    /// 人员入职时间。
    /// </summary>
    //private String checkInTime;

    /// <summary>
    /// 人员离职时间。
    /// </summary>
    // private String checkOutTime;

    /// <summary>
    /// 职务（长度30）。
    /// </summary>
    private String office;

    /// <summary>
    /// 工作岗位（长度30）。
    /// </summary>
    private String job;

    //free
    /// <summary>
    /// 盘查原因（长度200）。
    /// </summary>
    private String checkReason;

    public String getInfo() {
        String info = "PersonId=" + Utils.URLEncode(this.getPersonId()) + "&" +
                "PersonCompanyId=" + Utils.URLEncode(this.getPersonCompanyId()) + "&" +
                "PersonCompanyType=" + Utils.URLEncode(this.getPersonComapnyType()) + "&" +
                "PersonPoliceId=" + Utils.URLEncode(this.getPersonPoliceId()) + "&" +
                "PersonTerminalId=" + Utils.URLEncode(this.getPersonTerminalId()) + "&" +
                "PersonTransactionId=" + Utils.URLEncode(this.getPersonTransactionId()) + "&" +
                "PersonCardType=" + Utils.URLEncode(this.getPersonCardType()) + "&" +
                "PersonCardId=" + Utils.URLEncode(this.getPersonCardId()) + "&" +
                "PersonName=" + Utils.URLEncode(this.getPersonName()) + "&" +
                "PersonSex=" + Utils.URLEncode(this.getPersonSex()) + "&" +
                "PersonNation=" + Utils.URLEncode(this.getPersonNation()) + "&" +
                "PersonNative=" + Utils.URLEncode(this.getPersonNative()) + "&" +
                "PersonBirthday=" + Utils.URLEncode(this.getPersonBirthday()) + "&" +
                "PersonAddress=" + Utils.URLEncode(this.getPersonAddress()) + "&" +
                "PersonPhoneNumber=" + Utils.URLEncode(this.getPersonPhoneNumeber()) + "&" +
                "PersonEastPoint=" + Utils.URLEncode(this.getPersonEastPoint()) + "&" +
                "PersonNorthPoint=" + Utils.URLEncode(this.getPersonNorthPoint()) + "&" +
                //"PersonCardImage=" + Utils.URLEncode(this.PersonCardImage) + "&" +
                //"PersonRealImage=" + Utils.URLEncode(this.PersonRealImage) + "&" +
                "PersonComment=" + Utils.URLEncode(this.getPersonComment()) + "&" +
                "PersonStatus=" + Utils.URLEncode(this.getPersonStatus());

        if (!Utils.isEmptyString(this.getPersonCardImage()))
            info += "&PersonCardImage=" + Utils.URLEncodeImage(this.getPersonCardImage());

        if (!Utils.isEmptyString(this.getPersonRealImage()))
            info += "&PersonRealImage=" + Utils.URLEncodeImage(this.getPersonRealImage());
        if (this.getPersonComapnyType().equals("Company")) {
            info += "&CheckInTime=" + this.getCheckInTime() + "&Office=" + this.getOffice() + "&Job=" + this.getJob();
            if (this.getCheckOutTime() != null) {
                info += "&CheckOutTime=" + this.getCheckOutTime();
            }
        } else if (this.getPersonComapnyType().equals("Free")) {
            info += "&CheckReason=" + this.getCheckReason();
        } else {
            info += "&CheckInTime=" + this.getCheckInTime() + "&CheckOutTime=" + this.getCheckOutTime();
        }
        System.out.println(info);
//        if (mPerson instanceof CompanyPersonModel) {
//            CompanyPersonModel person = (CompanyPersonModel) mPerson;
//            info += "&CheckInTime=" + person.CheckInTime + "&" +
//                    "CheckOutTime=" + person.CheckOutTime + "&" +
//                    "Office=" + person.Office + "&" +
//                    "Job=" + person.Job;
//        } else if (mPerson instanceof LeasePersonModel) {
//            LeasePersonModel person = (LeasePersonModel) mPerson;
//            info += "&CheckInTime=" + person.CheckInTime + "&" +
//                    "CheckOutTime=" + person.CheckOutTime;
//        } else if (mPerson instanceof FreePersonModel) {
//            FreePersonModel person = (FreePersonModel) mPerson;
//            info += "&CheckInTime=" + person.CheckReason;
//        }
        return info;

    }

    public String getAutoCheckOut() {
        return AutoCheckOut;
    }

    public void setAutoCheckOut(String autoCheckOut) {
        AutoCheckOut = autoCheckOut;
    }

    public String getCheckInTime() {
        return CheckInTime;
    }

    public void setCheckInTime(String checkInTime) {
        CheckInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return CheckOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        CheckOutTime = checkOutTime;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonAddress() {
        return PersonAddress;
    }

    public void setPersonAddress(String personAddress) {
        PersonAddress = personAddress;
    }


    public String getPersonBirthday() {
        return PersonBirthday;
    }

    public void setPersonBirthday(String personBirthday) {
        PersonBirthday = personBirthday;
    }

    public String getPersonCardId() {
        return PersonCardId;
    }

    public void setPersonCardId(String personCardId) {
        PersonCardId = personCardId;
    }

    public String getPersonCardImage() {
        return PersonCardImage;
    }

    public void setPersonCardImage(String personCardImage) {
        PersonCardImage = personCardImage;
    }

    public String getPersonCardType() {
        return PersonCardType;
    }

    public void setPersonCardType(String personCardType) {
        PersonCardType = personCardType;
    }

    public String getPersonChangeTime() {
        return PersonChangeTime;
    }

    public void setPersonChangeTime(String personChangeTime) {
        PersonChangeTime = personChangeTime;
    }

    public String getPersonComapnyName() {
        return PersonComapnyName;
    }

    public void setPersonComapnyName(String personComapnyName) {
        PersonComapnyName = personComapnyName;
    }

    public String getPersonComapnyType() {
        return PersonComapnyType;
    }

    public void setPersonComapnyType(String personComapnyType) {
        PersonComapnyType = personComapnyType;
    }

    public String getPersonComment() {
        return PersonComment;
    }

    public void setPersonComment(String personComment) {
        PersonComment = personComment;
    }

    public String getPersonCompanyId() {
        return PersonCompanyId;
    }

    public void setPersonCompanyId(String personCompanyId) {
        PersonCompanyId = personCompanyId;
    }

    public String getPersonCreateTime() {
        return PersonCreateTime;
    }

    public void setPersonCreateTime(String personCreateTime) {
        PersonCreateTime = personCreateTime;
    }

    public String getPersonDate() {
        return PersonDate;
    }

    public void setPersonDate(String personDate) {
        PersonDate = personDate;
    }

    public String getPersonEastPoint() {
        return PersonEastPoint;
    }

    public void setPersonEastPoint(String personEastPoint) {
        PersonEastPoint = personEastPoint;
    }

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }


    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPersonNation() {
        return PersonNation;
    }

    public void setPersonNation(String personNation) {
        PersonNation = personNation;
    }

    public String getPersonNative() {
        return PersonNative;
    }

    public void setPersonNative(String personNative) {
        PersonNative = personNative;
    }

    public String getPersonNorthPoint() {
        return PersonNorthPoint;
    }

    public void setPersonNorthPoint(String personNorthPoint) {
        PersonNorthPoint = personNorthPoint;
    }

    public String getPersonPhoneNumeber() {
        return PersonPhoneNumeber;
    }

    public void setPersonPhoneNumeber(String personPhoneNumeber) {
        PersonPhoneNumeber = personPhoneNumeber;
    }

    public String getPersonPoliceId() {
        return PersonPoliceId;
    }

    public void setPersonPoliceId(String personPoliceId) {
        PersonPoliceId = personPoliceId;
    }

    public String getPersonRealImage() {
        return PersonRealImage;
    }

    public void setPersonRealImage(String personRealImage) {
        PersonRealImage = personRealImage;
    }

    public String getPersonSex() {
        return PersonSex;
    }

    public void setPersonSex(String personSex) {
        PersonSex = personSex;
    }

    public String getPersonStatus() {
        return PersonStatus;
    }

    public void setPersonStatus(String personStatus) {
        PersonStatus = personStatus;
    }

    public String getPersonTerminalId() {
        return PersonTerminalId;
    }

    public void setPersonTerminalId(String personTerminalId) {
        PersonTerminalId = personTerminalId;
    }

    public String getPersonTransactionId() {
        return PersonTransactionId;
    }

    public void setPersonTransactionId(String personTransactionId) {
        PersonTransactionId = personTransactionId;
    }

    public String getPersonAtLarge() {
        return PersonAtLarge;
    }

    public void setPersonAtLarge(String personAtLarge) {
        PersonAtLarge = personAtLarge;
    }

    public int getPersonIndex() {
        return PersonIndex;
    }

    public void setPersonIndex(int personIndex) {
        PersonIndex = personIndex;
    }

    public String getPersonUpload() {
        return PersonUpload;
    }

    public void setPersonUpload(String personUpload) {
        PersonUpload = personUpload;
    }
}
