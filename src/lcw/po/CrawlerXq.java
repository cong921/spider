package lcw.po;

import java.util.Date;

public class CrawlerXq {
    private Long xqId;

    private String xqUrl;

    private String xqUrlMd5;

    private String xqTitle;

    private Date xqPudate;

    private String zdName;

    private String lmName;

    private String sjfl;

    private Date loadTime;

    private String xqAurhor;

    private String xqExtendOne;

    private String xqExtendTwo;

    private String keyWord;

    private Integer isSimtitle;

    private Integer isExteurl;
    
    private String lmTop;
    
    private Integer sta;

    public String getLmTop() {
		return lmTop;
	}

	public void setLmTop(String lmTop) {
		this.lmTop = lmTop;
	}


	public Integer getSta() {
		return sta;
	}

	public void setSta(Integer sta) {
		this.sta = sta;
	}

	public Long getXqId() {
        return xqId;
    }

    public void setXqId(Long xqId) {
        this.xqId = xqId;
    }

    public String getXqUrl() {
        return xqUrl;
    }

    public void setXqUrl(String xqUrl) {
        this.xqUrl = xqUrl == null ? null : xqUrl.trim();
    }

    public String getXqUrlMd5() {
        return xqUrlMd5;
    }

    public void setXqUrlMd5(String xqUrlMd5) {
        this.xqUrlMd5 = xqUrlMd5 == null ? null : xqUrlMd5.trim();
    }

    public String getXqTitle() {
        return xqTitle;
    }

    public void setXqTitle(String xqTitle) {
        this.xqTitle = xqTitle == null ? null : xqTitle.trim();
    }

    public Date getXqPudate() {
        return xqPudate;
    }

    public void setXqPudate(Date xqPudate) {
        this.xqPudate = xqPudate;
    }

    public String getZdName() {
        return zdName;
    }

    public void setZdName(String zdName) {
        this.zdName = zdName == null ? null : zdName.trim();
    }

    public String getLmName() {
        return lmName;
    }

    public void setLmName(String lmName) {
        this.lmName = lmName == null ? null : lmName.trim();
    }

    public String getSjfl() {
        return sjfl;
    }

    public void setSjfl(String sjfl) {
        this.sjfl = sjfl == null ? null : sjfl.trim();
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }

    public String getXqAurhor() {
        return xqAurhor;
    }

    public void setXqAurhor(String xqAurhor) {
        this.xqAurhor = xqAurhor == null ? null : xqAurhor.trim();
    }

    public String getXqExtendOne() {
        return xqExtendOne;
    }

    public void setXqExtendOne(String xqExtendOne) {
        this.xqExtendOne = xqExtendOne == null ? null : xqExtendOne.trim();
    }

    public String getXqExtendTwo() {
        return xqExtendTwo;
    }

    public void setXqExtendTwo(String xqExtendTwo) {
        this.xqExtendTwo = xqExtendTwo == null ? null : xqExtendTwo.trim();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public Integer getIsSimtitle() {
        return isSimtitle;
    }

    public void setIsSimtitle(Integer isSimtitle) {
        this.isSimtitle = isSimtitle;
    }

    public Integer getIsExteurl() {
        return isExteurl;
    }

    public void setIsExteurl(Integer isExteurl) {
        this.isExteurl = isExteurl;
    }
}