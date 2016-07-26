package my.assignment.asynctaskdialog;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "AsyncTask";
    Button okbtn,cnclbtn;
    EditText numtxt;
    ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);



    }

    public void createDialog(View view){
        final Dialog dialoglayout;
        dialoglayout=new Dialog(MainActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialoglayout.setContentView(R.layout.dialog);
        dialoglayout.setCancelable(false);
        dialoglayout.show();
        okbtn=(Button)dialoglayout.findViewById(R.id.okbutton);
        cnclbtn=(Button)dialoglayout.findViewById(R.id.canclebutton);
        numtxt=(EditText)dialoglayout.findViewById(R.id.numText);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadTask().execute(Integer.parseInt(numtxt.getText().toString()));
                dialoglayout.dismiss();

            }
        });
        cnclbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoglayout.dismiss();
            }
        });


    }
    public class LoadTask extends AsyncTask<Integer,Integer,Integer
            > {
        @Override
        protected void onPreExecute(){
            mProgressBar.setVisibility(ProgressBar.VISIBLE);

        }

        @Override
        protected Integer doInBackground(Integer... delay) {
           sleep(delay[0]);
            sleep(delay[0]*1000);

            return null;
        }
        @Override
        protected void onPostExecute(Integer result){
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Successful");
            builder.setMessage("Task Completed!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();

        }
        private void sleep(Integer mDelay) {
            try {
                Thread.sleep(mDelay);
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            }
        }
    }
}
