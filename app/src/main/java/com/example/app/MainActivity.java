package com.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lamor.dialog.CustomDialog;
import com.lamor.dialog.IDialog;

/**
 * @author Master
 * @create 2018/8/24 15:59
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustomDialog iCustomDialog = new CustomDialog(this, R.layout.layout_custom_dialog, new int[]{R.id.dialog_cancel, R.id.dialog_ok}, true);
        iCustomDialog.setOnDialogItemClickListener(new CustomDialog.OnDialogItemClickListener() {
            @Override
            public void onDialogItemClick(CustomDialog dialog, View view) {
                TextView view1 = (TextView) view;
                Toast.makeText(MainActivity.this, view1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        iCustomDialog.show();


        new IDialog(this).builder()
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setTitle("就是极好的")
                .setSubTitle("谁也不能回到过去。向前看，那才是你未来的所在。")
                .setLeftButton("CANCEL", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setRightButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();


    }

}
