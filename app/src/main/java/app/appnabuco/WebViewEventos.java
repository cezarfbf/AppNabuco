package app.appnabuco;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by cezar on 17/06/2015.
 */
public class WebViewEventos extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        //if (Uri.parse(url).getHost().endsWith("joaquimnabuco.edu.br/evento/listar")){

        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        view.getContext().startActivity(intent);

        return true;
    }
}
