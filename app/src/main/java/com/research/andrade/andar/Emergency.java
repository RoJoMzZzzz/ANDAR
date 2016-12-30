package com.research.andrade.andar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private MediaPlayer mp;

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



        /*if (alarm.isChecked()) {
            PlayAlarm();
        }
        else {
            mp.stop();
        }*/

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

        emergencyContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),EmergencyContacts.class));
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
                if (b) {
                    savingMode.setChecked(false);
                    PlayAlarm();
                } else {
                    mp.stop();
                }

            }
        });

        return view;
    }


    private void PlayAlarm() {
        mp = MediaPlayer.create(getActivity(), R.raw.alarm);
        mp.setLooping(true);
        mp.start();
    }

}

