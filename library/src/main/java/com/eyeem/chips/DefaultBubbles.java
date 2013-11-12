package com.eyeem.chips;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;

/**
 * @author vishna
 */
public class DefaultBubbles {
   public static int LILA = 0;
   public static int GRAY = 1;
   public static int GRAY_WHITE_TEXT = 2;
   public static int PINK = 3;
   public static int GREEN = 4;
   public static int CITY_COUNTRY = 5;

   private static BubbleStyle array[];

   public static int v_spacing;
   public static int h_spacing;
   public static int long_bubble_workaround;

   public static BubbleStyle get(int type, Context context) {
      if (array == null) {
         init(context);
      }
      return array[type];
   }

   public static void init(Context context) {
      context = context.getApplicationContext();
      Resources r = context.getResources();
      int textSize = r.getDimensionPixelSize(R.dimen.bubble_text_size);
      int padding = r.getDimensionPixelSize(R.dimen.bubble_padding);
      v_spacing = r.getDimensionPixelSize(R.dimen.bubble_v_spacing);
      h_spacing = r.getDimensionPixelSize(R.dimen.bubble_h_spacing);

      array = new BubbleStyle[] {
         new BubbleStyle(
            context.getResources().getDrawable(R.drawable.lilatext_background_active),
            context.getResources().getDrawable(R.drawable.lilatext_background_pressed),
            textSize, 0xffebf5e0, 0xffebf5e0, padding), // LILA
         new BubbleStyle(
            context.getResources().getDrawable(R.drawable.greybubble_background),
            context.getResources().getDrawable(R.drawable.greybubble_background),
            textSize, 0xff404040, 0xff404040, padding), // GRAY
         new BubbleStyle(
            context.getResources().getDrawable(R.drawable.greybubble_background),
            context.getResources().getDrawable(R.drawable.greybubble_background),
            textSize, Color.WHITE, Color.WHITE, padding), // GRAY_WHITE_TEXT
         new BubbleStyle(
            context.getResources().getDrawable(R.drawable.pinktext_background_active),
            context.getResources().getDrawable(R.drawable.pinktext_background_pressed),
            textSize, 0xffffedff, 0xff574f57, padding), // PINK
         new BubbleStyle(
            context.getResources().getDrawable(R.drawable.greentext_background_active),
            context.getResources().getDrawable(R.drawable.greentext_background_pressed),
            textSize, 0xffebe0f5, 0xffebe0f5, padding), // GREEN
         new BubbleStyle(
            null, null,
            context.getResources().getDimensionPixelSize(R.dimen.bubble_country_text_size),
            0xffcccccc, 0xff000000, 0, false) // CITY_COUNTRY
      };

      // calculate workaround per device
      TextPaint paint = new TextPaint();
      float _dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, r.getDisplayMetrics());
      paint.setAntiAlias(true);
      paint.setTextSize(_dp);
      paint.setColor(Color.BLACK);
      long_bubble_workaround = (int)paint.measureText(" ");
      Log.i("CHIPS", "long_bubble_workaround = "+long_bubble_workaround);
   }
}