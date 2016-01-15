package liuliu.quzhang.model;

import java.io.Serializable;

import liuliu.quzhang.base.Utils;


/**
 * Created by Administrator on 2015/11/11.
 */
public class PersonModel implements Serializable {
    private String id;
    private String PersonId;//��Ա����
    private String PersonCompanyId;//��ҵ����
    private String PersonPoliceId;//�񾯱���
    private String PersonTerminalId;//�豸����
    private String PersonTransactionId;//�������
    private String PersonComapnyType;//��Ա������ҵ����
    private String PersonComapnyName;//��ҵ����
    private String PersonCardType;//֤������
    private String PersonCardId;//֤������
    private String PersonName;//��Ա����
    private String PersonSex;//�Ա�1��2Ů
    private String PersonNation;//����
    private String PersonNative;//����
    private String PersonBirthday;//��������
    private String PersonAddress;//��ϸ��ַ
    private String PersonPhoneNumeber;//��ϵ�绰
    /// <summary>
    /// ������
    /// </summary>
    private String PersonEastPoint;

    /// <summary>
    /// ��γ��
    /// </summary>
    private String PersonNorthPoint;

    /// <summary>
    /// ֤����Ƭ��
    /// </summary>
    private String PersonCardImage;

    /// <summary>
    /// ʵ����Ƭ��
    /// </summary>
    private String PersonRealImage;

    /// <summary>
    /// ��ע������200����
    /// </summary>
    private String PersonComment;

    /// <summary>
    /// ��Ա״̬��
    /// </summary>
    private String PersonStatus;

    /// <summary>
    /// ����ʱ�䡣
    /// </summary>
    private String PersonCreateTime;

    /// <summary>
    /// �޸�ʱ�䡣
    /// </summary>
    private String PersonChangeTime;
    /// <summary>
    /// ���ڡ�
    /// </summary>
    private String PersonDate;

    /// <summary>
    /// ��ˮ��š�
    /// </summary>
    private int PersonIndex;

    private String PersonUpload;

    private String PersonAtLarge;
    /// <summary>
    /// ����ʱ�䡣
    /// </summary>
    private String CheckInTime;

    /// <summary>
    /// ����ʱ�䡣
    /// </summary>
    private String CheckOutTime;

    /// <summary>
    /// �Ƿ���ϵͳ�Զ���ꡣ
    /// </summary>
    private String AutoCheckOut;
    //company
    /// <summary>
    /// ��Ա��ְʱ�䡣
    /// </summary>
    //private String checkInTime;

    /// <summary>
    /// ��Ա��ְʱ�䡣
    /// </summary>
    // private String checkOutTime;

    /// <summary>
    /// ְ�񣨳���30����
    /// </summary>
    private String office;

    /// <summary>
    /// ������λ������30����
    /// </summary>
    private String job;

    //free
    /// <summary>
    /// �̲�ԭ�򣨳���200����
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
