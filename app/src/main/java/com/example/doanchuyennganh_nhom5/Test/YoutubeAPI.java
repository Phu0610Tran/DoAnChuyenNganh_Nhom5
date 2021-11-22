package com.example.doanchuyennganh_nhom5.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {

    private static final  String BASE_URL = "https://www.googleapis.com/youtube/v3/";


    public  interface HomeVideo {
        @GET
        Call<ResponseBody> getYT (@Url String url);
    }

    public static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0) return null;
                    return delegate.convert(body);                }
            };
        }
    }



    private static HomeVideo homeVideo = null;



    public static HomeVideo getHomeVideo(){
        if(homeVideo == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();//If need to logging, just uncomment
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)

                    .addConverterFactory(new NullOnEmptyConverterFactory())

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            homeVideo = retrofit.create(HomeVideo.class);
        }
        return homeVideo;
    }
}
