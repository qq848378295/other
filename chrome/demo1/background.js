// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

'use strict';

chrome.runtime.onInstalled.addListener(function() {
  chrome.storage.sync.set({color: '#3aa757'}, function() {
    console.log('The color is green.');
  });
  chrome.declarativeContent.onPageChanged.removeRules(undefined, function() {
    chrome.declarativeContent.onPageChanged.addRules([{
      conditions: [new chrome.declarativeContent.PageStateMatcher({
      //  pageUrl: {hostEquals: 'developer.chrome.com'},
		pageUrl: { urlContains: '.' }, //url的内容中包含字母.的，插件才会被激活
      })],
      actions: [new chrome.declarativeContent.ShowPageAction()]
    }]);
  });
}); 




// 是否显示图片
//var showImage;
//chrome.storage.sync.get({showImage: true}, function(items) {
//   showImage = items.showImage;
//});


// web请求监听，最后一个参数表示阻塞式，需单独声明权限：webRequestBlocking
chrome.webRequest.onBeforeRequest.addListener(details => {
	 if('xmlhttprequest'==details.type &&  details.url.indexOf("msg")>0  &&  details.url.indexOf("short_video")>0){
		 chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
		    chrome.tabs.executeScript(
		        tabs[0].id,
		        // {code: 'console.log(\''+details.type+':::'+details.url+'\' );'}
				   // {code: 'document.getElementsByTagName(\"div\")[0].append(\"'+details.url+'\n\"); console.log("'+details.url+'")'}
				   
				   
				   //{code: 'document.getElementsByTagName(\"div\")[0].append(\"<data>'+details.url+'\");'}
				   {code: 'document.getElementById("wrap").append("<data>'+details.url+'</data>");'}
					// document.getElementById("wrap").append("a")				   
				   
				   
				   
				  // 这个可以显示  {code: 'document.getElementsByTagName(\"div\")[0].append(\"'+details.url+'[br]\");'}
				// {code: 'console.log(\''+ details.url.indexOf("msg")+':::'+details.url+'\' );'}
				);
				
		  });
	 }
	   
    // cancel 表示取消本次请求
   // if(!showImage && details.type == 'image') return {cancel: true};
 //   if(  details.type == 'fetch') 
	// return {cancel: true};
   
    // 简单的音视频检测
    // 大部分网站视频的type并不是media，且视频做了防下载处理，所以这里仅仅是为了演示效果，无实际意义
    //if(details.type == 'media') {
        //chrome.notifications.create(null, {
        //    type: 'basic',
       //     iconUrl: 'img/icon.png',
      //      title: '检测到音视频',
     //       message: '音视频地址：' + details.url,
    //    });
    //}
}, {urls: ["<all_urls>"]}, ["blocking"]);