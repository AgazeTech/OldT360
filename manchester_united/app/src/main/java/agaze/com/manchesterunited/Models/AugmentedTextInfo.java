package agaze.com.manchesterunited.Models;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by agaze on 22/7/17.
 */
public class AugmentedTextInfo { //this class renders a text as overlay on any surface

    String m_renderText,m_renderFond;
    int m_xcoor, m_ycoor;
    int m_fonsize,m_width,m_height;
    Context m_context;
    GLSurfaceView glSurfaceView;
    public AugmentedTextInfo(Context context){
        m_context = context;
        glSurfaceView = new TextRendererSurfaceView(m_context);

    }
    public AugmentedTextInfo(){

    }
    public void renderText(String text,String fond){
        m_renderText = text;
        m_renderFond = fond;
    }

    public void setFondSize(int size,int x, int y){
        m_fonsize = size;
        m_xcoor = x;
        m_ycoor = y;

    }
    public void setDimension(int width , int height){
        m_width = width;
        m_height = height;
    }

   class TextRendererSurfaceView extends GLSurfaceView {
       public TextRendererSurfaceView(Context context) {
           super(context);
           setEGLContextClientVersion(2);
           setRenderer(new AugmentedTextRenderer(context,m_renderText,m_renderFond,m_fonsize,m_xcoor,m_ycoor,m_width,m_height));
       }
   }


}
