package module

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream

class UtilsBitmap {

    companion object {
        fun new ( ctx: Context, filename: String ) : Bitmap {
            val assetManager = ctx.assets
            val input: InputStream = assetManager.open(filename)
            val bitmap: Bitmap = BitmapFactory.decodeStream(input)
            return bitmap
        }
    }
}