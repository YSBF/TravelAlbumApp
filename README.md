# TravelAlbumApp


### 问题收集 ###

1. 项目引用多个module必须保证每个module的Manifest文件的包名不一样


## WidgetLib ##

WidgetLib提供了App常用的控件（官方无直接实现），在快速开发一个App时可以直接依赖并使用。。

**注：** minSdkVersion 14

### 1. 初始化 ###

在App里面的创建MyApplication类继承于Application，并在onCreate()方法进行初始化WidgetLib：

    public class MyApplication extends Application {
	    @Override
	    public void onCreate() {
	        super.onCreate();
	
	        // init WidgetLib
	        WidgetLibManager.initWidgetLib(this);

			// ...
	    }

		// ...
	}

然后在AndroidManifest.xml文件里面，指定application标签name为MyApplication类的类名：

	<application
        android:name=".common.MyApplication"
		...
		>
		
		...
	</application>

## MvpLib ##

MvpLib提供了一个简单的MVP使用框架，在快速开发一个App时可以直接依赖并使用。

**注：** minSdkVersion 14

## Utils ##

Utils提供了一系列App中常用的工具类，在快速开发一个App时可以直接依赖并使用。