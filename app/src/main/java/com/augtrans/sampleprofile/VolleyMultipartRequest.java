package com.augtrans.sampleprofile;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.CharsetUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VolleyMultipartRequest extends Request<String> {

    MultipartEntityBuilder entity = MultipartEntityBuilder.create();
    HttpEntity httpentity;
    private final Response.Listener<String> mListener;
    private final HashMap<String, File> mFileParts;
    private final Map<String, String> header;

    public VolleyMultipartRequest(String url, Response.Listener<String> listener,
                                  Response.ErrorListener errorListener,
                                  HashMap<String, File> files,
                                  Map<String, String> header) {
        super(Method.POST, url, errorListener);
        this.mListener = listener;
        this.mFileParts = files;
        this.header = header;

        entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            entity.setCharset(CharsetUtils.get("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        buildMultipartEntity();
        httpentity = entity.build();

    }

    private void buildMultipartEntity() {
        if (mFileParts != null) {
            for (Map.Entry<String, File> entry : mFileParts.entrySet()) {
                entity.addPart(entry.getKey(), new FileBody(entry.getValue(), ContentType.create("image/*"), entry.getValue().getName()));
            }
        }
    }

    @Override
    public String getBodyContentType() {

        return httpentity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            httpentity.writeTo(bos);
        } catch (IOException ex){
            ex.printStackTrace();
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            Log.d("Network_Response ", new String(response.data, "UTF-8"));
            return Response.success(new String(response.data, "UTF-8"),
                    getCacheEntry());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.success(new String(response.data), getCacheEntry());
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (header == null) {
            return super.getHeaders();
        } else {
            return header;
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

}