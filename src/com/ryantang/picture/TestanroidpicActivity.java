package com.ryantang.picture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TestanroidpicActivity extends Activity {  
    /** Called when the activity is first created. */  
        Bitmap bp=null;  
        ImageView imageview;  
        float scaleWidth;  
        float scaleHeight;  
          
       int h;  
        boolean num=false;  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_testandroid);  
          
        Display display=getWindowManager().getDefaultDisplay();  
        imageview=(ImageView)findViewById(R.id.imageview);  
        bp=BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);  
        int width=bp.getWidth();  
        int height=bp.getHeight();  
        int w=display.getWidth();  
        int h=display.getHeight();  
        scaleWidth=((float)w)/width;  
        scaleHeight=((float)h)/height;  
        imageview.setImageBitmap(bp);  
    }  
        @Override  
        public boolean onTouchEvent(MotionEvent event) {  
                  
               switch(event.getAction()){  
                  
               case MotionEvent.ACTION_DOWN:  
                if(num==true)        {  
                        Matrix matrix=new Matrix();  
                        matrix.postScale(scaleWidth,scaleHeight);  
                          
                       Bitmap newBitmap=Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);  
                        imageview.setImageBitmap(newBitmap);  
                        num=false;  
                        }  
                else{  
                        Matrix matrix=new Matrix();  
                        matrix.postScale(1.0f,1.0f);  
                        Bitmap newBitmap=Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);  
                    imageview.setImageBitmap(newBitmap);  
                    num=true;  
                }  
                        break;  
                }  
                  
                 
               return super.onTouchEvent(event);  
        }  
  
     
         
     
     
}   