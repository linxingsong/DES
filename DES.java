import java.io.DataInputStream;
import java.io.IOException;


public class DES{
	public String[] keyArray=new String[4];
	public String[] encryptedArray= new String[4];
	public int[][] keyTable;
	public String keyLeft;
	public String keyRight;
	public String messageLeft;
	public String messageRight;
	public String EncryptedMessage;
	
	public DES(String key, String message){
		keyLeft = key.substring(0, key.length()/2);
	    keyRight = key.substring(key.length()/2, key.length());
	    System.out.println("First key seperator: "+ keyLeft+" "+keyRight);
	    generateKeyArray(keyLeft, keyRight, 0);
	    generateKeyArray(keyLeft, keyRight,1);
	    generateKeyArray(keyLeft, keyRight,2);
	    generateKeyArray(keyLeft, keyRight,3); 
	    System.out.print("gerenated 4 keys: ");
	    for(int i=0; i<4; i++){
	    	System.out.print(keyArray[i]+" ");
	    }
	    System.out.println();
	    
		messageLeft = message.substring(0, message.length()/2);
	    messageRight = message.substring(message.length()/2, message.length());
	    Encryption(0);
	    Encryption(1);
	    Encryption(2);
	    Encryption(3);
	    EncryptedMessage = messageLeft+messageRight;
	    System.out.println("\nOriginal Data: "+ message);
		System.out.println("Enrypted Data: "+ messageLeft+messageRight);
	    //System.out.println("EncryptedMessage is: "+EncryptedMessage);
	    messageLeft = EncryptedMessage.substring(0, EncryptedMessage.length()/2);
	    messageRight = EncryptedMessage.substring(EncryptedMessage.length()/2, EncryptedMessage.length());
	    System.out.println("\nEncryptedMessage: "+messageLeft+" "+messageRight);
	    Decryption(3);
	    Decryption(2);
	    Decryption(1);
	    Decryption(0);
	    String DecryptedMessage = messageLeft+ messageRight;
	    System.out.println("\nDecrypted message: "+ DecryptedMessage);
	    System.out.println("\nOriginal Data: "+ message);
		System.out.println("Decrypted Data: "+ messageLeft+messageRight);
	}
	
	void generateKeyArray(String left1, String right1, int n){
		String temp1 = left1.substring(left1.length()-1)+left1.substring(0,left1.length()-1);
		String temp2 = right1.substring(1,right1.length())+right1.substring(0,1);
		//System.out.println(temp1+"\n"+temp2);
		//System.out.println("key");
		String temp3 = temp1.substring(0,temp1.length()/2)+temp2.substring(0,temp2.length()/2);
		//System.out.println(temp3);
		keyArray[n] = temp3;
		keyLeft = temp1;
		keyRight = temp2;
	}
	
	String function(char a, char b){
		String line ="";
		if(a == b){
			line = line+"0";
		}
		else{
			line = line+"1";
		}
		return line;
	}
	
	void Encryption(int n){
		String functionLine="";
		String result="";
	    for(int i=0; i<keyArray[n].length(); i++){
	    	functionLine = functionLine+function(messageRight.charAt(i), keyArray[n].charAt(i));
	    }
	    for(int j=0; j<keyArray[n].length(); j++){
	    	result = result+ function(messageLeft.charAt(j), functionLine.charAt(j));
	    }
	    System.out.println("For Encryption, round "+n+" result: "+functionLine+" "+result);
	    messageLeft = messageRight;
	    messageRight = result;
	    //System.out.println(messageLeft+" "+messageRight);
	}
	
	void Decryption(int n){
		String functionLine="";
		String result= "";
		for(int i =0; i<keyArray[n].length(); i++){
			functionLine = functionLine+function(messageLeft.charAt(i), keyArray[n].charAt(i));
		}
		for(int j=0; j<keyArray[n].length(); j++){
			result = result+ function(messageRight.charAt(j), functionLine.charAt(j));
		}
	    System.out.println("For Decryption, round "+n+" result: "+functionLine+" "+result);
	    messageRight = messageLeft;
	    messageLeft = result;
	    //System.out.println(messageLeft+" "+messageRight);
	}
	public static void main(String[] args) throws NumberFormatException, IOException{
		//DataInputStream dis = new DataInputStream(System.in);
		//System.out.println("Enter 8 bit Key, 0/1: ");
		//int Key = Integer.parseInt(dis.readLine(),2);
	    //System.out.println("Enter the 8 Bit message To be Encrypt  : ");
	    //int Message = Integer.parseInt(dis.readLine(),2);
		int Key = 1522113113;
		int Message = 1712233241;
	    String key = Integer.toBinaryString(Key);
	    System.out.println("Original Key in bits:"+key);
	    String message = Integer.toBinaryString(Message);
	    System.out.println("Original Message in bits:" +message);
	    DES des = new DES(key, message);

	}
	
}