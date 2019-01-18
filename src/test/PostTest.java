package test;

public class PostTest {
	/**
	 * 从本地excel表格中解析数据
	 */
//	public class exportDataFromExcel {
//	    public static List<City> readExcel(String path) {
//	        List<City> list = null;
//	        try {
//	            File excelFile = new File(path); //创建文件对象
//	            FileInputStream is = new FileInputStream(excelFile); //文件流
//	            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010
//	            int sheetCount = workbook.getNumberOfSheets();
//	            list = new ArrayList<City>(); //存储数据容器
//	            for (int s = 0; s < sheetCount; s++) {
//	                Sheet sheet = workbook.getSheetAt(s);
//	                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数   
//	                System.out.println("行:"+rowCount);
//	                for (int r = 0; r < rowCount; r++) {
//	                    Row row = sheet.getRow(r);
//	                    row.getCell(0).setCellType(CellType.STRING);
//	                    row.getCell(1).setCellType(CellType.STRING);
//	                    String id = row.getCell(0).getStringCellValue();
//	                    String areCode = row.getCell(1).getStringCellValue();
//	                    City city = new City();
//	                    city.setId(Integer.valueOf(id));
//	                    city.setAreaCode(areCode);
//	                    System.out.println("id" + city.getId() +";areCode"+city.getAreaCode());
//	                    list.add(city);
//	                }
//	            }
//	            is.close();
//	        }
//	        catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return list;
//	    }
//
//	    /**
//	     * post调用接口的方法（传的参数不能是数组）
//	     * @param url
//	     * @param city
//	     * @return
//	     */
//	    public static String sendPost(String url, String city) {
//	        OutputStreamWriter out = null;
//	        BufferedReader in = null;
//	        String result = "";
//	        try {
//	            URL realUrl = new URL(url);
//	            HttpURLConnection conn = null;
//	            // 打开和URL之间的连接
//	            conn = (HttpURLConnection) realUrl.openConnection();
//	            // 发送POST请求必须设置如下两行
//	            conn.setDoOutput(true);
//	            conn.setDoInput(true);
//	            conn.setRequestMethod("POST");    // POST方法
//
//	            // 设置通用的请求属性
//	            conn.setRequestProperty("accept", "*/*");
//	            conn.setRequestProperty("connection", "Keep-Alive");
//	            conn.setRequestProperty("user-agent",
//	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//	            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//	            conn.connect();
//	            // 获取URLConnection对象对应的输出流
//	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//	            // 发送请求参数
//	            out.write(city);
//	            // flush输出流的缓冲
//	            out.flush();
//	            // 定义BufferedReader输入流来读取URL的响应
//	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	            String line;
//	            while ((line = in.readLine()) != null) {
//	                result += line;
//	            }
//	        } catch (Exception e) {
//	            System.out.println("发送 POST 请求出现异常！" + e);
//	            e.printStackTrace();
//	        }
//	        //使用finally块来关闭输出流、输入流
//	        finally {
//	            try {
//	                if (out != null) {
//	                    out.close();
//	                }
//	                if (in != null) {
//	                    in.close();
//	                }
//	            } catch (IOException ex) {
//	                ex.printStackTrace();
//	            }
//	        }
//	        return result;
//	    }
//
//	    /***
//	     * post方式调用接口，参数不限制（多学习）
//	     * @param cityList
//	     * @param postpath
//	     * @return
//	     */
//	        public static String httpPostTool(List<City> cityList, String postpath) {
//	            CloseableHttpClient httpclient = HttpClients.createDefault();
//	            // 创建httppost
//	            HttpPost httppost = new HttpPost(postpath);
////	            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
////	            for (int i = 0 ; i <cityList.size() ; i++) {
////	                formparams.add(new BasicNameValuePair(cityList.get(i).getId().toString(),cityList.get(i).getAreaCode()));
////	            }
//	            List<JSONObject> jsonObjects = new ArrayList<>();
//	            for (int i = 0 ; i <cityList.size() ; i++) {
//	                JSONObject jsonObject = new JSONObject();
//	                jsonObject.put("id",cityList.get(i).getId());
//	                jsonObject.put("areaCode",cityList.get(i).getAreaCode());
//	                jsonObjects.add(jsonObject);
//	            }
//	            // 创建参数队列
////	            UrlEncodedFormEntity uefEntity;
//	            String responseString = "";
//	            try {
////	                uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//	                httppost.setHeader("Content-Type", "application/json;charset=utf-8");
////	                httppost.setEntity(uefEntity);
//	                httppost.setEntity(new StringEntity(jsonObjects.toString()));
//	                CloseableHttpResponse response = httpclient.execute(httppost);
//	                HttpEntity entity = response.getEntity();
//	                if (entity != null) {
//	                    // 调用接口返回的字符串
//	                    responseString += EntityUtils.toString(entity, "UTF-8");
//	                }
//	                response.close();
//	            } catch (UnsupportedEncodingException e1) {
//	                e1.printStackTrace();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            } finally {
//	                // 关闭连接,释放资源
//	                try {
//	                    httpclient.close();
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	            return responseString;
//	        }
//	    public static void main(String[] args) {
//	        List<City> lists = readExcel("/Users/dimengze/Documents/file5.xlsx");
//	        if (lists.size()!=0) {
////	            for (int i = 0; i < lists.size(); i++) {
////	                sendPost("http://localhost:8082/bd_web_war/city/modifyById", JSON.toJSONString(lists.get(i)));
////	            }
//	            try {
//	               httpPostTool(lists, "http://localhost:8082/bd_web_war/city/modifyById");  //参数 ，url
//	            }catch (Exception e){
//	                System.out.println("出现异常！");
//	            }
//	            System.out.println("数据更改完成！");
//	        }else {
//	            System.out.println("文件没有数据！");
//	        }
//	    }
//
//	}


}
