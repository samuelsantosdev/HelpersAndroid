# HelpersAndroid

###NumberFormat
Apply a numeric mask for a EditText in TextWatcher.

#####Params
- In new instance the 'Constructor' receive the current TextWatcher.
- setEditable = EditText
- setMask = (String) with mask "####/##/##"
- setCurrency = (boolean) For currency mask
- setReverse = (boolean) Apply mask in reverse order, right to left (true), left to right (false)
- apply = Apply the mask in EditText

#####Example
Insert this code in your class Activity -> method onCreate, with your EditText in 'findViewById(R.id.number)'

	final EditText number = (EditText) findViewById(R.id.number);
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    NumberFormat strFormatter = new NumberFormat(this);
                    strFormatter.setEditable(number);
                    strFormatter.setMask("###.###.###,##");
                    strFormatter.setCurrency(true);
                    strFormatter.setReverse(true);
                    strFormatter.apply();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

