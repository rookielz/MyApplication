package rookiez.top.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import rookiez.top.myapplication.R;

/**
 * Created by Administrator on 2016/9/25.
 */

public class ImageLoading {

    public static File cacheDir = null;

    public ImageLoading(Context context){
        cacheDir = StorageUtils.getOwnCacheDirectory(context, "Nmi/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
            // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(480, 800)
//线程池内加载的数量
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
//将保存的时候的URI名称用MD5 加密
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
// 你可以通过自己的内存缓存实现
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
// 内存缓存的最大值
                .memoryCacheSize(2 * 1024 * 1024)
// 50 Mb sd卡(本地)缓存的最大值
                .diskCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
// 自定义缓存路径由,原先的discCache -> diskCache
                .diskCache(new UnlimitedDiskCache(cacheDir))
// connectTimeout (5 s), readTimeout (30 s)超时时间
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
                .writeDebugLogs() // Remove for release app
                .build();
//全局初始化此配置
        ImageLoader.getInstance().init(config);
    }
    public void showImage(final String url, final ImageView img){
        new Runnable(){
            @Override
            public void run() {
                ImageLoader imageLoader = ImageLoader.getInstance();//得到单例
                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        .showImageForEmptyUri(R.mipmap.loading)//占位显示图片
                        .showImageOnFail(R.mipmap.error)//加载失败显示图片
                        .showImageOnLoading(R.mipmap.loading)//加载中显示图片
                        .cacheInMemory(false)//缓存到内存
                        .cacheOnDisk(true)//缓存到磁盘
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();
                imageLoader.displayImage(url,img,options);
            }
        }.run();
    }
}
