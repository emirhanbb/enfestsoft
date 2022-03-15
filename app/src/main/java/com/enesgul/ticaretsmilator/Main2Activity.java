package com.enesgul.ticaretsmilator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.OneSignal;

public class Main2Activity extends AppCompatActivity {
    String webUrl = "https://enfessoft.com/ticaretsimulator/";
    private WebView webView;
    private ProgressBar mProgressBar;

    BottomNavigationView bottomNavigationView;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private PermissionRequest myRequest;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 101;
    private static final String ONESIGNAL_APP_ID = "53874a38-1732-4ebb-8632-39a54e82947c";


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(Main2Activity.this.getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mProgressBar = findViewById(R.id.pb);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            webUrl = extras.getString("link");

        }
        else{
            System.out.println(extras);
            webUrl = "https://enfessoft.com/ticaretsimulator/";


        }

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


        bottomNavigationView = findViewById(R.id.bottommenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(menulistener);

        webView = findViewById(R.id.webview1);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);


        WebSettings webSettings = webView.getSettings();
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        webSettings.setDatabaseEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSettings.setGeolocationEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setGeolocationDatabasePath(Main2Activity.this.getFilesDir().getPath() );
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);



        if (Build.VERSION.SDK_INT > 17) { webSettings.setMediaPlaybackRequiresUserGesture(false); } // mp3




        if(savedInstanceState !=null){
            webView.restoreState(savedInstanceState);
        }
        else
        {
            checkConnection();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl(webUrl);
                }
            });
        }




        webView.loadUrl(webUrl);




        webView.setWebViewClient(new WebViewClient() {


            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                try {
                    webView.stopLoading();
                } catch (Exception e) {
                }

                if (webView.canGoBack()) {
                    webView.goBack();
                }

                webView.loadUrl("about:blank");
                Intent intent2 = new Intent(Main2Activity.this,Connection.class);
                startActivity(intent2);
                finish();
                super.onReceivedError(webView, errorCode, description, failingUrl);
            }


            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                if(url.startsWith("tel:") || url.startsWith("whatsapp:") || url.startsWith("facebook.com")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                }

                if(url.startsWith("https://www.instagram.com/")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                    return true;

                }




                        webView.loadUrl(url);




                return false;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Visible the progressbar
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
            }



        });



        webView.setWebChromeClient(new WebChromeClient() {


            private View mCustomView;
            private WebChromeClient.CustomViewCallback mCustomViewCallback;
            protected FrameLayout mFullscreenContainer;
            private int mOriginalOrientation;
            private int mOriginalSystemUiVisibility;

            public Bitmap getDefaultVideoPoster()
            {
                if (mCustomView == null) {
                    return null;
                }
                return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
            }

            public void onHideCustomView()
            {
                ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
                this.mCustomView = null;
                getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
                setRequestedOrientation(this.mOriginalOrientation);
                this.mCustomViewCallback.onCustomViewHidden();
                this.mCustomViewCallback = null;
            }

            public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
            {
                if (this.mCustomView != null)
                {
                    onHideCustomView();
                    return;
                }
                this.mCustomView = paramView;
                this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
                this.mOriginalOrientation = getRequestedOrientation();
                this.mCustomViewCallback = paramCustomViewCallback;
                ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
                getWindow().getDecorView().setSystemUiVisibility(3846);
            }




        });



    }


    private BottomNavigationView.OnNavigationItemSelectedListener menulistener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){

                case R.id.market:
                    Intent intent2 = new Intent(Main2Activity.this,OdemeTuru.class);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.mesajlar:
                    webView.loadUrl("https://enfessoft.com/ticaretsimulator/gelenkutusu.php");
                    break;
                case R.id.siralama:
                    webView.loadUrl("https://enfessoft.com/ticaretsimulator/siralama.php");
                    break;

                case R.id.sohbet:
                    webView.loadUrl("https://enfessoft.com/ticaretsimulator/sohbet.php");
                    break;
            }
            return true;
        }
    };


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.loadUrl("https://enfessoft.com/ticaretsimulator/");
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Uygulamadan Çık");
            dialog.setMessage("Uygulamadan çıkmak üzeresiniz, ne yapmak istersiniz ?");
            dialog.setPositiveButton("ÇIK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.setCancelable(false);
            dialog.setNegativeButton("KAL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();






        }
    }



    public void checkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if(wifi.isConnected()){
            webView.loadUrl(webUrl);



        }
        else if (mobileNetwork.isConnected()){
            webView.loadUrl(webUrl);

        }
        else{

            Intent intent2 = new Intent(Main2Activity.this,Connection.class);
            startActivity(intent2);
            finish();
        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        webView.saveState(outState);
    }


}