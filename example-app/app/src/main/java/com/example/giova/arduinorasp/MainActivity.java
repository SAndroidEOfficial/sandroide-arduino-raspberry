package com.example.giova.arduinorasp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import it.unibs.sandroide.lib.BLEContext;
import it.unibs.sandroide.lib.activities.SandroideBaseActivity;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIOEvent;
import it.unibs.sandroide.lib.item.generalIO.BLEOnGeneralIOEventListener;

public class MainActivity extends SandroideBaseActivity {
    protected static final String TAG = "MainActivity";

    TextView tvArduinoButton, tvArduinoLed, tvRaspALed, tvRaspAButton, tvRaspBLed, tvRaspBButton;
    BLEGeneralIO arduinoButton, arduinoLed, raspALed, raspAButton, raspBLed, raspBButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvArduinoButton =(TextView) findViewById(R.id.tvArduinoButton);
        tvArduinoLed    =(TextView) findViewById(R.id.tvArduinoLed);
        tvRaspALed      =(TextView) findViewById(R.id.tvRaspALed);
        tvRaspAButton   =(TextView) findViewById(R.id.tvRaspAButton);
        tvRaspBLed      =(TextView) findViewById(R.id.tvRaspBLed);
        tvRaspBButton   =(TextView) findViewById(R.id.tvRaspBButton);

        arduinoLed = (BLEGeneralIO) BLEContext.findViewById("arduino_rbs_general_io_5");
        arduinoButton = (BLEGeneralIO) BLEContext.findViewById("arduino_rbs_general_io_2");

        raspALed = (BLEGeneralIO) BLEContext.findViewById("raspA_raspi_general_io_5");
        raspAButton = (BLEGeneralIO) BLEContext.findViewById("raspA_raspi_general_io_2");

        raspBLed = (BLEGeneralIO) BLEContext.findViewById("raspB_raspi_general_io_5");
        raspBButton = (BLEGeneralIO) BLEContext.findViewById("raspB_raspi_general_io_2");

        arduinoLed.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                arduinoLed.setStatus(BLEGeneralIO.GENERAL_IO_DO);
            }

            @Override
            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
            }

            @Override
            public void onDigitalOutputValueChanged(final BLEGeneralIOEvent bleGeneralIOEvent) {
                runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          tvArduinoLed.setTextColor(bleGeneralIOEvent.values[1]==1?Color.BLUE:Color.RED);
                      }
                  }
                );
            }

            @Override
            public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {

            }
        });

        arduinoButton.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                arduinoButton.setStatus(BLEGeneralIO.GENERAL_IO_DI);
            }

            @Override
            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.d(TAG, "arduino button pressing: "+ bleGeneralIOEvent.values[1]);
                if(bleGeneralIOEvent.values[1]==1)
                {
                    Log.d(TAG, "arduino led: setting HIGH");
                    raspALed.setDigitalValueHigh(true);
                    runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              tvArduinoButton.setTextColor(Color.BLUE);
                          }
                      }
                    );
                } else {
                    Log.d(TAG, "arduino led: setting LOW");
                    raspALed.setDigitalValueHigh(false);
                    runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              tvArduinoButton.setTextColor(Color.RED);
                          }
                      }
                    );
                }
            }

            @Override
            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onDigitalOutputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {

            }
        });


        raspALed.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                raspALed.setStatus(BLEGeneralIO.GENERAL_IO_DO);
            }

            @Override
            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
            }

            @Override
            public void onDigitalOutputValueChanged(final BLEGeneralIOEvent bleGeneralIOEvent) {
                runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          tvRaspALed.setTextColor(bleGeneralIOEvent.values[1]==1?Color.BLUE:Color.RED);
                      }
                  }
                );
            }

            @Override
            public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {

            }
        });

        raspAButton.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                raspAButton.setStatus(BLEGeneralIO.GENERAL_IO_DI);
            }

            @Override
            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.d(TAG, "raspA button pressing: "+ bleGeneralIOEvent.values[1]);
                if(bleGeneralIOEvent.values[1]==0)
                {
                    Log.d(TAG, "raspA led: setting HIGH");
                    raspBLed.setDigitalValueHigh(true);
                    runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              tvRaspAButton.setTextColor(Color.BLUE);
                          }
                      }
                    );
                } else {
                    Log.d(TAG, "raspA led: setting LOW");
                    raspBLed.setDigitalValueHigh(false);
                    runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              tvRaspAButton.setTextColor(Color.RED);
                          }
                      }
                    );
                }
            }

            @Override
            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onDigitalOutputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {

            }
        });

        try {

            raspBLed.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
                @Override
                public void onBoardInitEnded() {
                    raspBLed.setStatus(BLEGeneralIO.GENERAL_IO_DO);
                }

                @Override
                public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                }

                @Override
                public void onDigitalOutputValueChanged(final BLEGeneralIOEvent bleGeneralIOEvent) {
                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          tvRaspBLed.setTextColor(bleGeneralIOEvent.values[1] == 1 ? Color.BLUE : Color.RED);
                                      }
                                  }
                    );
                }

                @Override
                public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {

                }
            });

            raspBButton.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
                @Override
                public void onBoardInitEnded() {
                    raspBButton.setStatus(BLEGeneralIO.GENERAL_IO_DI);
                }

                @Override
                public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.d(TAG, "raspB button pressing: " + bleGeneralIOEvent.values[1]);
                    if (bleGeneralIOEvent.values[1] == 0) {
                        Log.d(TAG, "raspB led: setting HIGH");
                        arduinoLed.setDigitalValueHigh(true);
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              tvRaspBButton.setTextColor(Color.BLUE);
                                          }
                                      }
                        );
                    } else {
                        Log.d(TAG, "raspB led: setting LOW");
                        arduinoLed.setDigitalValueHigh(false);
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              tvRaspBButton.setTextColor(Color.RED);
                                          }
                                      }
                        );
                    }
                }

                @Override
                public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onDigitalOutputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

                }

                @Override
                public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {

                }
            });
        } catch(RuntimeException ex) {
            BLEContext.displayToastOnMainActivity(ex.toString());
            ex.printStackTrace();
        }
    }
}