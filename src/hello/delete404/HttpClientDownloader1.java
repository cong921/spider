package hello.delete404;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
/*     */ import java.util.HashMap;
import java.util.List;
/*     */ import java.util.Map;

/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.annotation.ThreadSafe;
/*     */ import org.apache.http.client.methods.CloseableHttpResponse;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;

/*     */ import us.codecraft.webmagic.Page;
/*     */ import us.codecraft.webmagic.Request;
/*     */ import us.codecraft.webmagic.Site;
/*     */ import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;
import us.codecraft.webmagic.downloader.HttpUriRequestConverter;
/*     */ import us.codecraft.webmagic.proxy.Proxy;
/*     */ import us.codecraft.webmagic.proxy.ProxyProvider;
/*     */ import us.codecraft.webmagic.selector.PlainText;
/*     */ import us.codecraft.webmagic.utils.CharsetUtils;
/*     */ import us.codecraft.webmagic.utils.HttpClientUtils;
import util.JdbcUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ @ThreadSafe
/*     */ public class HttpClientDownloader1
/*     */   extends AbstractDownloader
/*     */ {
/*     */   public HttpClientDownloader1() {}
/*     */   
/*  36 */   private Logger logger = LoggerFactory.getLogger(getClass());
/*     */   
/*  38 */   private final Map<String, CloseableHttpClient> httpClients = new HashMap();
/*     */   
/*  40 */   private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();
/*     */   
/*  42 */   private HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
/*     */   
/*     */   private ProxyProvider proxyProvider;
/*     */   
/*  46 */   private boolean responseHeader = true;
/*     */   
/*     */   public void setHttpUriRequestConverter(HttpUriRequestConverter httpUriRequestConverter) {
/*  49 */     this.httpUriRequestConverter = httpUriRequestConverter;
/*     */   }
/*     */   
/*     */   public void setProxyProvider(ProxyProvider proxyProvider) {
/*  53 */     this.proxyProvider = proxyProvider;
/*     */   }
/*     */   
/*     */   private CloseableHttpClient getHttpClient(Site site) {
/*  57 */     if (site == null) {
/*  58 */       return this.httpClientGenerator.getClient(null);
/*     */     }
/*  60 */     String domain = site.getDomain();
/*  61 */     CloseableHttpClient httpClient = (CloseableHttpClient)this.httpClients.get(domain);
/*  62 */     if (httpClient == null) {
/*  63 */       synchronized (this) {
/*  64 */         httpClient = (CloseableHttpClient)this.httpClients.get(domain);
/*  65 */         if (httpClient == null) {
/*  66 */           httpClient = this.httpClientGenerator.getClient(site);
/*  67 */           this.httpClients.put(domain, httpClient);
/*     */         }
/*     */       }
/*     */     }
/*  71 */     return httpClient;
/*     */   }
/*     */   
/*     */   public Page download(Request request, Task task)
/*     */   {
/*  76 */     if ((task == null) || (task.getSite() == null)) {
/*  77 */       throw new NullPointerException("task or site can not be null");
/*     */     }
/*  79 */     CloseableHttpResponse httpResponse = null;
/*  80 */     CloseableHttpClient httpClient = getHttpClient(task.getSite());
/*  81 */     Proxy proxy = this.proxyProvider != null ? this.proxyProvider.getProxy(task) : null;
/*  82 */     HttpClientRequestContext requestContext = this.httpUriRequestConverter.convert(request, task.getSite(), proxy);
/*  83 */     Page page = Page.fail();
/*     */     try {
/*  85 */       httpResponse = httpClient.execute(requestContext.getHttpUriRequest(), requestContext.getHttpClientContext());
/*  86 */       page = handleResponse(request, request.getCharset() != null ? request.getCharset() : task.getSite().getCharset(), httpResponse, task);
/*  87 */       onSuccess(request);
/*  88 */       this.logger.info("downloading page success {}", request.getUrl());
				Object obj=new Object();
				synchronized (obj) {
					if(page.getStatusCode()==404) {
						JdbcUtil jdbcUtil = new JdbcUtil();
						jdbcUtil.getConnection();
						try {
							List<String> list = new ArrayList<String>();
							list.add(page.getUrl().toString());
							boolean result = jdbcUtil.updateByPreparedStatement(
									"delete  from   crawler_xq where xq_url=?",list);
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							jdbcUtil.releaseConn();
						}
						logger.warn("删除"+page.getUrl());
					}
				}
				
/*  89 */       return page;
/*     */     } catch (IOException e) {
/*  91 */       this.logger.warn("download page {} errorahhahah", request.getUrl(), e);
/*  92 */       onError(request);
/*  93 */       return page;
/*     */     } finally {
/*  95 */       if (httpResponse != null)
/*     */       {
/*  97 */         EntityUtils.consumeQuietly(httpResponse.getEntity());
/*     */       }
/*  99 */       if ((this.proxyProvider != null) && (proxy != null)) {
/* 100 */         this.proxyProvider.returnProxy(proxy, page, task);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setThread(int thread)
/*     */   {
/* 107 */     this.httpClientGenerator.setPoolSize(thread);
/*     */   }
/*     */   
/*     */   protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
/* 111 */     byte[] bytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
/* 112 */     String contentType = httpResponse.getEntity().getContentType() == null ? "" : httpResponse.getEntity().getContentType().getValue();
/* 113 */     Page page = new Page();
/* 114 */     page.setBytes(bytes);
/* 115 */     if (!request.isBinaryContent()) {
/* 116 */       if (charset == null) {
/* 117 */         charset = getHtmlCharset(contentType, bytes);
/*     */       }
/* 119 */       page.setCharset(charset);
/* 120 */       page.setRawText(new String(bytes, charset));
/*     */     }
/* 122 */     page.setUrl(new PlainText(request.getUrl()));
/* 123 */     page.setRequest(request);
/* 124 */     page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
/* 125 */     page.setDownloadSuccess(true);
/* 126 */     if (this.responseHeader) {
/* 127 */       page.setHeaders(HttpClientUtils.convertHeaders(httpResponse.getAllHeaders()));
/*     */     }
/* 129 */     return page;
/*     */   }
/*     */   
/*     */   private String getHtmlCharset(String contentType, byte[] contentBytes) throws IOException {
/* 133 */     String charset = CharsetUtils.detectCharset(contentType, contentBytes);
/* 134 */     if (charset == null) {
/* 135 */       charset = Charset.defaultCharset().name();
/* 136 */       this.logger.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()", Charset.defaultCharset());
/*     */     }
/* 138 */     return charset;
/*     */   }
/*     */ }

/* Location:           C:\Users\taihao\eclipse-workspace\hello\lib\webmagic-core-0.7.3.jar
 * Qualified Name:     us.codecraft.webmagic.downloader.HttpClientDownloader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */