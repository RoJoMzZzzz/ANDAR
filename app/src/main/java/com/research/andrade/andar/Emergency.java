package com.research.andrade.andar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Emergency extends Fragment {

    private Switch savingMode, flashlight, strobeFlashlight, screenFlashlight, alarm;
    private Button messageContacts, emergencyContacts, callAuthority, angQRnaIto;
    private Camera camera;
    private Boolean isLightOn;
    private Camera.Parameters p;

    public Emergency() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);

        savingMode = (Switch) view.findViewById(R.id.swtSavingMove);
        flashlight = (Switch) view.findViewById(R.id.swtFlashlight);
        strobeFlashlight = (Switch) view.findViewById(R.id.swtStrobe);
        screenFlashlight = (Switch) view.findViewById(R.id.swtScreenFlash);
        alarm = (Switch) view.findViewById(R.id.swtAlarm);

        messageContacts = (Button) view.findViewById(R.id.btnMessage);
        emergencyContacts = (Button) view.findViewById(R.id.btnContacts);
        callAuthority = (Button) view.findViewById(R.id.btnCall);
        angQRnaIto = (Button) view.findViewById(R.id.btnQRCode);

        /*checkCamera();
        getCamera();
        checkSwitches();*/

        angQRnaIto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MyQR.class));
            }
        });


        messageContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MessageContact.class));
            }
        });

        savingMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (savingMode.isChecked()) {
                    alarm.setChecked(false);
                    Toast.makeText(getActivity(), "Battery Saving Mode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        flashlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (flashlight.isChecked()) {
                    screenFlashlight.setChecked(false);
                    strobeFlashlight.setChecked(false);

                } else {

                }
            }
        });

        strobeFlashlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (strobeFlashlight.isChecked()) {
                    flashlight.setChecked(false);
                    screenFlashlight.setChecked(false);

                }
            }
        });

        screenFlashlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (screenFlashlight.isChecked()) {
                    flashlight.setChecked(false);
                    strobeFlashlight.setChecked(false);
                    Toast.makeText(getActivity(), "Screen Flashlight", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (alarm.isChecked()) {
                    savingMode.setChecked(false);
                    Toast.makeText(getActivity(), "Alarm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void checkCamera() {
        Context ctx = getActivity();

        boolean hasFlash = ctx.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {

            flashlight.setEnabled(false);
            strobeFlashlight.setEnabled(false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Flashlight not supported!")
                    .setCancelable(true)
                    .setTitle("Error")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }


    public void checkSwitches() {
        /*if (savingMode.isChecked()) {
            alarm.setChecked(false);
            Toast.makeText(getActivity(), "Battery Saving Mode", Toast.LENGTH_SHORT).show();
        }

        if (flashlight.isChecked()) {
            screenFlashlight.setChecked(false);
            strobeFlashlight.setChecked(false);
            turnOnFlash();
        }

        if (flashlight.isChecked()== false) {
            turnOffFlash();
        }

        if (strobeFlashlight.isChecked()) {
            flashlight.setChecked(false);
            screenFlashlight.setChecked(false);
            BlinkFlash();
        }

        if (strobeFlashlight.isChecked()== false) {
            turnOffFlash();
        }

        if (screenFlashlight.isChecked()) {
            flashlight.setChecked(false);
            strobeFlashlight.setChecked(false);
            Toast.makeText(getActivity(), "Screen Flashlight", Toast.LENGTH_SHORT).show();
        }

        if (alarm.isChecked()) {
            savingMode.setChecked(false);
            Toast.makeText(getActivity(), "Alarm", Toast.LENGTH_SHORT).show();
        }
        */

    }

    // getting camera parameters
    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                p = camera.getParameters();
            } catch (RuntimeException e) {
                Toast.makeText(getActivity(),""+e,Toast.LENGTH_LONG).show();
            }
        }
    }


    public void turnOnFlash(){
        if (!isLightOn) {
            if (camera == null || p == null) {
                return;
            }
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();
        isLightOn = true;

        }
    }

    public void turnOffFlash(){
        if (isLightOn) {
            if (camera == null || p == null) {
                return;
            }
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(p);
        camera.stopPreview();
        camera.setPreviewCallback(null);
        isLightOn = false;
        }
    }

    private void BlinkFlash(){
        String myString = "010101010101";
        long blinkDelay = 50; //Delay in ms
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == '0') {
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(p);
                camera.startPreview();
                isLightOn = true;

            } else {
                p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(p);
                camera.stopPreview();
                isLightOn = false;

            }
            try {
                Thread.sleep(blinkDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*@Override
    public void onStart() {
        super.onStart();
        getCamera();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
        turnOffFlash();
    }

    *//*@Override
    public void onResume() {
        super.onResume();
        if (isLightOn) {
            turnOnFlash();
        } else
            turnOffFlash();

    }*//*

    @Override
    public void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }*/
}

