# LoadingView
###加载样式切换控件
此控件主要为方便页面加载过程控制，一件更改当前页面显示状态（加载中、加载成功、加载失败、网络异常）<br>
#####使用方式
<img src="https://github.com/Ivan-Jun/LoadingView/blob/master/app/screen/xml_screen.png" width="600px"  />
<br>
`
loading.setLoadingState(LoadingView.LOADING);//加载中
loading.setLoadingState(LoadingView.NET_ERROR);//网络异常
loading.setLoadingState(LoadingView.ERROR);//加载失败
loading.setLoadingState(LoadingView.EMPTY);//页面为空
loading.setLoadingState(LoadingView.NORMAL);//加载成功
`
<br>
效果图<br>
<img src="https://github.com/Ivan-Jun/LoadingView/blob/master/app/screen/loading_screen.png" alt="Drawing" width="320px" />
<br>

