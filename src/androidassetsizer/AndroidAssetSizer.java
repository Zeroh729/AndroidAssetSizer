package androidassetsizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import net.coobird.thumbnailator.Thumbnails;

public class AndroidAssetSizer {
    private static File res = new File("res");
    private static String[] folderNames = {"ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi"};
        
    public static void main(String[] args) throws IOException{
        ArrayList<File> fileList = new ArrayList();
        getFilenamesInCurDir(fileList);
        createAndroidAssets(fileList);
        resizePngFiles(fileList);
        JOptionPane.showMessageDialog(null, "Done.");
    }
    
    public static void getFilenamesInCurDir(ArrayList<File> fileList) throws IOException{
        File[] files = new File(System.getProperty("user.dir")).listFiles();
        for(File f : files){
            if(f.isFile()){
                fileList.add(f);
                System.out.println("names: " + f.getName() + " " + f.getCanonicalPath());
            }
        }
    }
    
    public static void createAndroidAssets(ArrayList<File> fileList) throws IOException{
        if(!res.exists()){
            try{
                res.mkdir();
            }catch(SecurityException e){
                
            }
            for(String folderName : folderNames){
                File dpi = new File("res//" + folderName);
                if(!dpi.exists()){
                    try{
                        dpi.mkdir();
                    }catch(SecurityException e){

                    }
                }
            }
        }
    }
    
    public static void resizePngFiles(ArrayList<File> fileList) throws IOException{
        double ldpi = 0.1875; //Base is xxxhdpi (2560 x 1440)
        double mdpi = 0.25;
        double hdpi = 0.375;
        double xhdpi = 0.50;
        double xxhdpi = 0.75;
        double xxxhdpi = 1; 
        double[] scaleSizes = {ldpi, mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi};
        
        
        for(File file : fileList){
            if(file.getName().contains(".png")){
                for(int i = 0; i < 6; i++)
                Thumbnails.of(file).scale(scaleSizes[i]).toFile(System.getProperty("user.dir") + "//res//" + folderNames[i] + "//" + file.getName());
            }
        }
    }}
