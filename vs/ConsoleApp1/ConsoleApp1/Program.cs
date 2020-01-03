using System;
using System.IO;
using System.Net;
using System.Text; 
namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {

            /*
            Console.WriteLine(string.Format("接收到了{0}个参数", args.Length));
            foreach (var item in args){
                Console.WriteLine(item); 
            }
            Thread.Sleep(500);//睡眠500毫秒，也就是0.5秒 
            */
            //  String url = "http://www.baidu.com";
            String url = "https://bigsubs-api.uc.cn/api/bigsubs_personal/msgs?uc_param_str=frdnpfvecpntgibiniprdswiutmt&app=ucweb&sub_type=personal&b_version=0.4&errCode=2&errMsg=ucapi.invoke%20not%20exsit%2C%20should%20load%20in%20UCBrowser%20%21&ut=AAQuLl0WSFNXJwCIgccPZnizKN9OiLo2pkPjd5oIoq2jnA%3D%3D&size=12&wmId=84229754edf544798a3868966dcb6980&pos=4&col_id=muggle&col_cont_src=short_video&col_name=%E5%B0%8F%E8%A7%86%E9%A2%91&col_type=video&index=2&wm_id=84229754edf544798a3868966dcb6980&max_pos="
                +1;
            foreach (var item in args)
            {
                Console.WriteLine(item);
                //a(item);
                b(item);
            }
            b(url);
            Console.WriteLine("url:"+url);
            Console.Read(); //让控制台暂停,否则一闪而过了    


        }
         

        private static void b(string url)
        {
            WebRequest request = WebRequest.Create(url);
            WebResponse response = request.GetResponse();
            StreamReader reader = new StreamReader(response.GetResponseStream(), 
                //Encoding.GetEncoding("gb2312")
                Encoding.GetEncoding("utf-8")
                );
            Console.WriteLine(reader.ReadToEnd());
        }

        private static void a(String url)
        {
            try
            {
                WebClient MyWebClient = new WebClient();
                MyWebClient.Credentials = CredentialCache.DefaultCredentials;//获取或设置用于向Internet资源的请求进行身份验证的网络凭据
                Byte[] pageData = MyWebClient.DownloadData(url); //从指定网站下载数据
                string pageHtml = Encoding.Default.GetString(pageData);  //如果获取网站页面采用的是GB2312，则使用这句    
                Console.Write(pageHtml);//在控制台输入获取的内容
                Console.Write("webClient:"+url);
                /*using (StreamWriter sw = new StreamWriter("D:\\test\\ouput.html"))//将获取的内容写入文本
                {
                    sw.Write(pageHtml);
                }*/
              
            }catch (WebException webEx)
            {
                Console.WriteLine(webEx.Message.ToString());
            }
        }
    }
}
