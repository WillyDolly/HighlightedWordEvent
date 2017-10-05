package com.popland.pop.highlightedwordevent;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.ClipboardManager;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView tvHighlight;
    Button btnPaste;
    String styledText;
    WebView webView;
    ClipboardManager clipboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHighlight = (TextView)findViewById(R.id.TVhightlight);
        btnPaste =(Button)findViewById(R.id.BTNpaste);
        webView = (WebView)findViewById(R.id.webView);
        //styledText = "This is <font color='blue'>simple</font>. <size>45</size>";
        //tvHighlight.setText(Html.fromHtml(styledText)); //get Text from HTML code
        webView.loadUrl("http://www.thanhniennews.com/tech/");
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                webView.evaluateJavascript("(function(){return window.getSelection().toString()})()",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                tvHighlight.setText(value.trim());
                            }
                        });
                return false;
            }
        });

//        btnPaste.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
//                if(clipboard.hasPrimaryClip()){
//                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
//                    tvHighlight.setText(item.getText().toString());
//                }
//
//            }
//        });
    }



//    public void selectText(){ //put this method into a MenuItem
//        KeyEvent shiftPressEvent = new KeyEvent(0,0,KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_SHIFT_LEFT,0,0);
//        shiftPressEvent.dispatch(webView);// send events press$drag to highlight words to a WebView
//    }
}
