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
            foreach (var item in args)
            {
                Console.WriteLine(item); 
            }
            Thread.Sleep(500);//睡眠500毫秒，也就是0.5秒 
            */
            String url = "http://www.baidu.com";
            foreach (var item in args)
            {
                Console.WriteLine(item);
                //a(item);
                b(item);
            }
            b(url);
            Console.Read(); //让控制台暂停,否则一闪而过了    


        }
         

        private static void b(string url)
        {
            WebRequest request = WebRequest.Create("http://www.baidu.com/");
            WebResponse response = request.GetResponse();
            StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.GetEncoding("gb2312"));
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
