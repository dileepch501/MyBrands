package com.dileep.org.myworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TestMessage extends AppCompatActivity {

    EditText phonnum,message;
    Button submit;
   static String url = "https://www.way2sms.com";
   static String apikey="QZW5G9BN1OCAFOVAYKFKVA14IDWECOSN";
   static String secretkey="R97IYO74UHQYF4ST";
    String api="http://hindit.co.in/API/pushsms.aspx?loginID=$login_Id$&password=$password$&mobile=98********&text=$message$&senderid=******&route_id=1&Unicode=0&IP=x.x.x.x";

  /*  String loginid="loginID="+"P1Iqbal";
    String password="&password="+"39386";
    String mobile="&mobile="+"7729958790,8688996943";
    String textmessage="&text="+"hii from dileep";
    String senderid="&senderid="+"";
    String routeid="&route_id="+"";
    String unicode="&Unicode="+"";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_message);

        phonnum=(EditText)findViewById(R.id.phonnum);
        message=(EditText)findViewById(R.id.message);
        submit=(Button)findViewById(R.id.submit);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendSms();
              //  sendCampaign("MNY3ZVYZAJM3V5L5PFVH85IH9VYQ9TC5","CYAEEFGSEI8OS6RA","stage",phonnum.getText().toString(),message.getText().toString(),"default");

            // System.out.println("userid creation"+createSenderId("dileep",apikey,secretkey,"stage"));
            }
        });


    }




    public void sendSms() {
        try {
            // Construct data

            String loginid="loginID="+"P1Iqbal";
            String password="&password="+"39386";
            String mobile="&mobile="+"7729958790,8688996943";
            String textmessage="&text="+"hii from dileep";
            String senderid="&senderid="+"";
            String routeid="&route_id="+"3";
            String unicode="&Unicode="+"1";
            String ip="";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("http://hindit.co.in/API/pushsms.aspx?").openConnection();
            String data = loginid + password + mobile + textmessage+senderid+routeid+unicode+ip;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

           // return stringBuffer.toString();
            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
            System.out.println("success:"+stringBuffer.toString());
        } catch (Exception e) {
            System.out.println("Error SMS " + e);
           // return "Error " + e;
            System.out.println("FAILED:"+e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public  String createSenderId(String senderId, String apiKey,String secretKey,String usetype){
        try{
            // construct data
            JSONObject urlParameters = new JSONObject();
            urlParameters.put("apikey", apiKey);
            urlParameters.put("secret",secretKey);
            urlParameters.put("usetype",usetype);
            urlParameters.put("senderid", senderId);
            URL obj = new URL(url + "/api/v1/createSenderId");
            // send data

            HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(urlParameters.toString().getBytes());
            // get the response
            BufferedReader bufferedReader = null;
            if (httpConnection.getResponseCode() == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
            Toast.makeText(this, content.toString(), Toast.LENGTH_SHORT).show();
            System.out.println("content:"+content.toString());
            return content.toString();
        }catch(Exception ex){
            System.out.println("Exception at createSenderId():"+ex.getMessage());
            return "{'status':500,'message':'Internal Server Error'}";
        }

    }


            public  String sendCampaign(String apiKey,String secretKey,String useType, String phone, String message1, String senderId){
                try{
                    // construct data
                    JSONObject urlParameters = new JSONObject();
                    urlParameters.put("apikey",apiKey);
                    urlParameters.put("secret",secretKey);
                    urlParameters.put("usetype",useType);
                    urlParameters.put("phone", phone);
                    urlParameters.put("message", URLEncoder.encode(message1,"UTF-8"));
                    urlParameters.put("senderid", senderId);
                    URL obj = new URL(url + "/api/v1/sendCampaign");
                    // send data
                    HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
                    httpConnection.setDoOutput(true);
                    httpConnection.setRequestMethod("POST");
                    DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
                    wr.write(urlParameters.toString().getBytes());
                    // get the response
                    BufferedReader bufferedReader = null;
                    if (httpConnection.getResponseCode() == 200) {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                    } else {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
                    }
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    bufferedReader.close();
                    Toast.makeText(this, content.toString(), Toast.LENGTH_SHORT).show();
                    return content.toString();
                }catch(Exception ex){
                    System.out.println("Exception at:"+ex);
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    return "{'status':500,'message':'Internal Server Error'}";
                }

            }



}
