package ctrlc;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import java.awt.Toolkit;

/**
 * 設定字串到剪貼簿
 */

public class ctrlC implements ClipboardOwner{
    private Clipboard clipboard;

    public ctrlC() {
        CutBookInit();
    }

    public void CutBookInit(){
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    /**
     * 設定剪貼內容
     */   
    public void setBookContents(String str){
        StringSelection contents = new StringSelection(str);
        clipboard.setContents(contents, this);
    }

    /**
     * 取出剪貼內容
     */
    public String getBookContents(){
        Transferable content = clipboard.getContents(this);
        try{
            return (String) content.getTransferData(DataFlavor.stringFlavor);
        }catch(Exception e){
            e.printStackTrace();
            //System.out.println(e);
        }
        return null;
    }




    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //System.out.println("lostOwnership...");
    }
  
    public static void main(String[] arg)
	{
		ctrlC a = new ctrlC();
		a.setBookContents("Hello~~I am CtrlC");
		System.out.println(a.getBookContents());
	}
   
}
