package lcw.po;

public class CrawlerXqWithBLOBs extends CrawlerXq {
    private String xqContent;

    private String xqExtendThree;

    private String xqExtendFour;

    private String xqExtendFive;

    private String xqExtendSixe;

    public String getXqContent() {
        return xqContent;
    }

    public void setXqContent(String xqContent) {
        this.xqContent = xqContent == null ? null : xqContent.trim();
    }

    public String getXqExtendThree() {
        return xqExtendThree;
    }

    public void setXqExtendThree(String xqExtendThree) {
        this.xqExtendThree = xqExtendThree == null ? null : xqExtendThree.trim();
    }

    public String getXqExtendFour() {
        return xqExtendFour;
    }

    public void setXqExtendFour(String xqExtendFour) {
        this.xqExtendFour = xqExtendFour == null ? null : xqExtendFour.trim();
    }

    public String getXqExtendFive() {
        return xqExtendFive;
    }

    public void setXqExtendFive(String xqExtendFive) {
        this.xqExtendFive = xqExtendFive == null ? null : xqExtendFive.trim();
    }

    public String getXqExtendSixe() {
        return xqExtendSixe;
    }

    public void setXqExtendSixe(String xqExtendSixe) {
        this.xqExtendSixe = xqExtendSixe == null ? null : xqExtendSixe.trim();
    }
}