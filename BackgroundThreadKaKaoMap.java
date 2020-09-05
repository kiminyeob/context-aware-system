class BackgroundThreadKaKaoMap extends Thread {

    /*

    [참고]

        editText는 class에서 public variable로 선언되어 있어야 한다.

     */

    /*

    [to do]

        1. url_address의 request를 변수로 튜닝할 있도록 코드를 수정하면 좋을 것 같음

        String x;
        String y;
        String size;
        String page;

        2. 인터넷에 연결이 안되어 있거나, 결과 값을 받아오지 못할 시에 처리할 코드 작성

    */

    String keyword = editText.getText().toString();
    String result = "";

    public void run(){

        String url_address = "https://dapi.kakao.com/v2/local/search/keyword.json?page=1&size=15&sort=distance&query="+keyword+"&x=0&y=0";

        if (keyword != "") {

            try {
                URL url = new URL(url_address);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "KakaoAK 1b6cd110fcde5815d96f676aaab63db2");
                InputStream is = conn.getInputStream();

                InputStreamReader responseBodyReader = new InputStreamReader(is, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                //Get the stream
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                // Set the result
                result = builder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("test",result);
                textView.setText(result);
            }
        });
    }
}
