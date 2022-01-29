package telegrambot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * @author Amirmohammad
 */

public class MyBot extends TelegramLongPollingBot {

    private static String SeriesName;
    private static int Season;
    private SendMessage sendMessage ;
    private SendPhoto sendImage;
    private boolean image ;
    private SendVideo sendMovie;
    private boolean movie;
    private static int counter = 1 ;

    @Override
    public String getBotToken() {
        return "1941560312:AAF1n1-AnpTc0i6HH4uaXXXtYgx0_ZZoDpg";
    }

    @Override
    public String getBotUsername() {
        return "TestJavaMovieBot";
    }


    @Override
    public void onUpdateReceived(Update update) {

        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        String Uname = update.getMessage().getFrom().getFirstName();
        String Uid = update.getMessage().getFrom().getUserName();

        // Date & Time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        try (BufferedWriter bf = new BufferedWriter(new FileWriter("Data.txt" , true )); ){

            bf.write( counter + ")  Name : " + Uname + "\t ID : " + Uid + "\t Date : " + date + "\t Time : " + time + "\t Text : " + text );
            bf.newLine();
            counter ++ ;

        } catch ( Exception e ){
            System.out.println(e.getMessage());
        }




        sendMessage = setSendMessage(chatId , text);
        image = false;
        movie = false;

        switch ( text ){

            case "/start" :

                sendMessage = setWelcomeMessage(chatId);
                sendMessage.setReplyMarkup(setSelectKeyboard());
                break;

            case "Home":

                sendMessage.setReplyMarkup(setSelectKeyboard());
                break;

            case "Series":

                sendMessage.setReplyMarkup(setSeriesKeyboard());
                break;

            case "Peaky Blinders":

                image = true;
                sendImage = setPeakyPoster(chatId);
                sendMessage.setReplyMarkup(setPeakySeasons());
                SeriesName = "Peaky Blinders";
                break;

            case "Season 1":

                if ( SeriesName.equals("Peaky Blinders") ){

                    Season = 1;
                    sendMessage.setReplyMarkup( setPeakyEpisodes());
                    break;
                }else if ( SeriesName.equals("Vikings") ){

                    Season = 1;
                    sendMessage.setReplyMarkup(setVikingsEpisodesSeason1());
                    break;
                }else if ( SeriesName.equals("Chernobyl")){

                    Season = 1;
                    sendMessage.setReplyMarkup(setChernobylEpisodes());
                    break;
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    Season = 1;
                    sendMessage.setReplyMarkup(setGotEpisodes());
                    break;
                }

            case "Season 2":

                if ( SeriesName.equals("Peaky Blinders") ){

                    Season = 2;
                    sendMessage.setReplyMarkup( setPeakyEpisodes());
                    break;
                }else if ( SeriesName.equals("Vikings") ){

                    Season = 2;
                    sendMessage.setReplyMarkup(setVikingsEpisodesSeason2());
                    break;
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    Season = 2;
                    sendMessage.setReplyMarkup(setGotEpisodes());
                    break;
                }

            case "Season 3":

                Season = 3;
                sendMessage.setReplyMarkup(setPeakyEpisodes());
                break;

            case "Show Peaky Seasons":

                image = true;
                sendImage = setPeakyPoster(chatId);
                sendMessage.setReplyMarkup(setPeakySeasons());
                break;

            case "Show Series List":

                sendMessage.setReplyMarkup(setSeriesKeyboard());
                break;

            case "Vikings":

                image = true;
                sendImage = setVikingsPoster(chatId);
                sendMessage.setReplyMarkup(setVikingsSeasons());
                SeriesName = "Vikings";
                break;

            case "Chernobyl":

                image = true;
                sendImage = setChernobylPoster(chatId);
                sendMessage.setReplyMarkup(setChernobylSeasons());
                SeriesName = "Chernobyl";
                break;

            case "Show Chernobyl Seasons":

                image = true;
                sendImage = setChernobylPoster(chatId);
                sendMessage.setReplyMarkup(setChernobylSeasons());
                break;

            case "Show Vikings Seasons":

                image = true;
                sendImage = setVikingsPoster(chatId);
                sendMessage.setReplyMarkup(setVikingsSeasons());
                break;

            case "Game Of Thrones":

                image = true;
                sendImage = setGOTPoster(chatId);
                sendMessage.setReplyMarkup(setGotSeasons());
                SeriesName = "Game Of Thrones";
                break;

            case "Show GOT Seasons":

                image = true;
                sendImage = setGOTPoster(chatId);
                sendMessage.setReplyMarkup(setGotSeasons());
                break;

            case "Movie":

                sendMessage.setReplyMarkup(setMovieKeyboard());
                break;

            case "Episode 1":

                if ( SeriesName.equals("Peaky Blinders") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setPeakyS1E1(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setPeakyS2E1(chatId);
                    }

                    if ( Season == 3 ){

                        movie = true;
                        sendMovie = setPeakyS3E1(chatId);
                    }

                } else if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E1(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E1(chatId);
                    }
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E1(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E1(chatId);
                    }

                } else  if ( SeriesName.equals("Chernobyl") ){

                    movie = true;
                    sendMovie = setChernobylS1E1(chatId);
                }
                break;



            case "Episode 2":

                if ( SeriesName.equals("Peaky Blinders") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setPeakyS1E2(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setPeakyS2E2(chatId);
                    }

                    if ( Season == 3 ){

                        movie = true;
                        sendMovie = setPeakyS3E2(chatId);
                    }

                } else if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E2(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E2(chatId);
                    }
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E2(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E2(chatId);
                    }

                } else  if ( SeriesName.equals("Chernobyl") ){

                    movie = true;
                    sendMovie = setChernobylS1E2(chatId);
                }
                break;

            case "Episode 3":

                if ( SeriesName.equals("Peaky Blinders") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setPeakyS1E3(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setPeakyS2E3(chatId);
                    }

                    if ( Season == 3 ){

                        movie = true;
                        sendMovie = setPeakyS3E3(chatId);
                    }

                }else if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E3(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E3(chatId);
                    }
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E3(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E3(chatId);
                    }

                } else  if ( SeriesName.equals("Chernobyl") ){

                    movie = true;
                    sendMovie = setChernobylS1E3(chatId);
                }
                break;

            case "Episode 4":

                if ( SeriesName.equals("Peaky Blinders") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setPeakyS1E4(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setPeakyS2E4(chatId);
                    }

                    if ( Season == 3 ){

                        movie = true;
                        sendMovie = setPeakyS3E4(chatId);
                    }

                }else if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E4(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E4(chatId);
                    }
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E4(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E4(chatId);
                    }

                } else  if ( SeriesName.equals("Chernobyl") ){

                    movie = true;
                    sendMovie = setChernobylS1E4(chatId);
                }
                break;

            case "Episode 5":

                if ( SeriesName.equals("Peaky Blinders") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setPeakyS1E5(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setPeakyS2E5(chatId);
                    }

                    if ( Season == 3 ){

                        movie = true;
                        sendMovie = setPeakyS3E5(chatId);
                    }

                }else if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E5(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E5(chatId);
                    }
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E5(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E5(chatId);
                    }

                } else  if ( SeriesName.equals("Chernobyl") ){

                    movie = true;
                    sendMovie = setChernobylS1E5(chatId);
                }
                break;

            case "Episode 6":

                if ( SeriesName.equals("Peaky Blinders") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setPeakyS1E6(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setPeakyS2E6(chatId);
                    }

                    if ( Season == 3 ){

                        movie = true;
                        sendMovie = setPeakyS3E6(chatId);
                    }

                }else if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E6(chatId);
                    }

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E6(chatId);
                    }
                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E6(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E6(chatId);
                    }

                }
                break;


            case "Episode 7":

                if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E7(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E7(chatId);
                    }

                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E7(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E7(chatId);
                    }

                }
                break;

            case "Episode 8":

                if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E8(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E8(chatId);
                    }

                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E8(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E8(chatId);
                    }

                }
                break;

            case "Episode 9":

                if ( SeriesName.equals("Vikings") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setVikingsS1E9(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E9(chatId);
                    }

                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E9(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E9(chatId);
                    }

                }
                break;

            case "Episode 10":

                if ( SeriesName.equals("Vikings") ){

                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setVikingsS2E10(chatId);
                    }

                } else if ( SeriesName.equals("Game Of Thrones") ){

                    if ( Season == 1 ){

                        movie = true;
                        sendMovie = setGotS1E10(chatId);
                    }
                    if ( Season == 2 ){

                        movie = true;
                        sendMovie = setGotS2E10(chatId);
                    }

                }
                break;


            ///////////////// End Of Series ///////////////

            case "Fast & furious 9":

                image = true;
                SeriesName = "Fast & furious 9";
                sendImage = setFastPoster(chatId);
                sendMessage.setReplyMarkup(setQualityKeyboard());
                break;

            case "Central intelligence":

                image = true;
                SeriesName = "Central intelligence";
                sendImage = setCentralPoster(chatId);
                sendMessage.setReplyMarkup(setQualityKeyboard());
                break;

            case "Ice road":

                image = true;
                SeriesName = "Ice road";
                sendImage = setIcePoster(chatId);
                sendMessage.setReplyMarkup(setQualityKeyboard());
                break;

            case "Tides":

                image = true;
                SeriesName = "Tides";
                sendImage = setTidesPoster(chatId);
                sendMessage.setReplyMarkup(setQualityKeyboard());
                break;


            ////////////////  Send Movie  /////////////////

            case "480" :

                if ( SeriesName.equals("Fast & furious 9" )){

                    movie = true;
                    sendMovie = setFast480(chatId);
                } else if ( SeriesName.equals("Central intelligence" )){

                    movie = true;
                    sendMovie = setCentral480(chatId);
                } else if ( SeriesName.equals("Ice road" )){

                    movie = true;
                    sendMovie = setIce480(chatId);
                } else if ( SeriesName.equals("Tides" )){

                    movie = true;
                    sendMovie = setTides480(chatId);
                }
                break;

            case "720":

                if ( SeriesName.equals("Fast & furious 9" )){

                    movie = true;
                    sendMovie = setFast720(chatId);
                } else if ( SeriesName.equals("Central intelligence" )){

                    movie = true;
                    sendMovie = setCentral720(chatId);
                } else if ( SeriesName.equals("Ice road" )){

                    movie = true;
                    sendMovie = setIce720(chatId);
                } else if ( SeriesName.equals("Tides" )){

                    movie = true;
                    sendMovie = setTides720(chatId);
                }
                break;

            case "Back to movie":

                sendMessage.setReplyMarkup(setMovieKeyboard());
                break;




        }





        try {
            execute(sendMessage);

        } catch (TelegramApiException ex) {
            Logger.getLogger(MyBot.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ( image ){

            try {
                sendPhoto(sendImage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (  movie ){

            try {
                sendVideo(sendMovie);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }



    }


    private SendMessage setWelcomeMessage( long chatId ){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Welcome !");

        return sendMessage;
    }

    private SendMessage setSendMessage( long chatId , String text ){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("you selected :  " + text );

        return sendMessage;
    }

    private ReplyKeyboardMarkup setSelectKeyboard(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Series");
        row.add("Movie");
        rowList.add(row);
        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setSeriesKeyboard(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Peaky Blinders");
        row.add("Vikings");
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Game Of Thrones");
        row.add("Chernobyl");
        rowList.add(row);
        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setPeakySeasons(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1 ; i < 4 ; i++) {

            row.add( "Season " + String.valueOf(i) );
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Series List");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setPeakyEpisodes(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1 ; i < 7 ; i++) {

            row.add( "Episode " + String.valueOf(i) );

            if ( i == 3 ){

                rowList.add(row);
                row = new KeyboardRow();
            }
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Peaky Seasons");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setVikingsSeasons(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1; i < 3; i++) {

            row.add( "Season " + String.valueOf(i) );
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Series List");
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setVikingsEpisodesSeason1(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1 ; i < 10 ; i++) {

            row.add( "Episode " + String.valueOf(i) );

            if ( i == 3 || i == 6 ){

                rowList.add(row);
                row = new KeyboardRow();
            }
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Vikings Seasons");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setVikingsEpisodesSeason2(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1 ; i < 11 ; i++) {

            row.add( "Episode " + String.valueOf(i) );

            if ( i == 4 || i == 8 ){

                rowList.add(row);
                row = new KeyboardRow();
            }
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Vikings Seasons");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setChernobylSeasons(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Season 1");
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Series List");
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setChernobylEpisodes(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1 ; i < 6 ; i++) {

            row.add( "Episode " + String.valueOf(i) );

            if ( i == 3 ){

                rowList.add(row);
                row = new KeyboardRow();
            }
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Chernobyl Seasons");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setGotSeasons(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1 ; i < 3 ; i++) {

            row.add( "Season " + String.valueOf(i) );
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show Series List");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setGotEpisodes(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 1 ; i < 11 ; i++) {

            row.add( "Episode " + String.valueOf(i) );

            if ( i == 4 || i == 8 ){

                rowList.add(row);
                row = new KeyboardRow();
            }
        }
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Show GOT Seasons");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);

        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setMovieKeyboard(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Fast & furious 9");
        row.add("Central intelligence");
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Ice road");
        row.add("Tides");
        rowList.add(row);
        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup setQualityKeyboard(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("720");
        row.add("480");
        rowList.add(row);

        row = new KeyboardRow();
        row.add("Back to movie");
        rowList.add(row);
        row = new KeyboardRow();
        row.add("Home");
        rowList.add(row);
        keyboardMarkup.setKeyboard(rowList);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }

    private SendPhoto setPeakyPoster(long chatId){

        SendPhoto sendImage = new SendPhoto();
        sendImage.setChatId(chatId);
        sendImage.setPhoto("AgACAgQAAxkBAAIDEmEHrhnTCZqyvldZ2qNsfkCosCnbAAO3MRtFnkBQ8dcQHaHQ9zsBAAMCAAN5AAMgBA");
        sendImage.setCaption(
                "\uD83D\uDCA2نام سریال: Peaky Blinders\n" +
                        "⭐️ژانر: #جنایی ، #درام\n" +
                        "\uD83C\uDFA5سال انتشار : 2013\n" +
                        "\uD83C\uDF96امتیاز :8.8/10\n" +
                        "\uD83D\uDC65بازیگران: Cillian Murphy, Paul Anderson, Helen McCrory, Sophie Rundle, Ned Dennehy\n" +
                        "\uD83D\uDCCDمحصول: انگلستان\n" +
                        "\uD83D\uDCDDخلاصه داستان: در دهه ۲۰ که گروه های گنگستری در شهر بیرمنگهام واقع در انگلستان بسیار زیاد شده است، یک گروه گنگستر سعی میکند خود را در این دهه به قدرت برساند...\n" +
                        "\n" +
                        "\n" +
                        "\uD83C\uDD94 @TestJavaMovieBot");

        return sendImage;
    }

    private SendPhoto setChernobylPoster(long chatId){

        SendPhoto sendImage = new SendPhoto();
        sendImage.setChatId(chatId);
        sendImage.setPhoto("AgACAgQAAxkBAAIDcWEIK1xHkw_TJvh9nWZszVMeBV0oAALRtzEbzwVBUNuX6PoudX7zAQADAgADeQADIAQ");
        sendImage.setCaption(
                "\uD83D\uDCA2نام سریال: Chernobyl/چرنوبیل\n" +
                        "⚜️فصل: اول\n" +
                        "\uD83D\uDD31قسمت: فصل اول کامل\n" +
                        "⭐️ژانر: #درام\n" +
                        "\uD83C\uDFA5سال انتشار : 2019\n" +
                        "\uD83C\uDF96امتیاز :9.4/10\n" +
                        "\uD83D\uDC65بازیگران: Joshua Leese, Ross Armstrong, Philip Barantini, Jessie Buckley, James Cosmo\n" +
                        "\uD83D\uDCCDمحصول: آمریکا , انگلستان\n" +
                        "\uD83D\uDCDDخلاصه داستان: در آوریل ۱۹۸۶ انفجاری در تأسیسات هسته\u200Cای چرنوبیل به یکی از فجایع انسانی در تاریخ تبدیل می\u200Cشود.\n" +
                        "\n" +
                        "\n" +
                        "\uD83C\uDD94 @TestJavaMovieBot");

        return sendImage;
    }

    private SendPhoto setVikingsPoster(long chatId){

        SendPhoto sendImage = new SendPhoto();
        sendImage.setChatId(chatId);
        sendImage.setPhoto("AgACAgQAAxkBAAIDf2EILnOCg24PP21mpp69TWRuLg5aAALUtzEbzwVBUJJlv6vaaU2BAQADAgADeQADIAQ");
        sendImage.setCaption(
                "\uD83D\uDCA2نام سریال: Vikings\n" +
                        "⭐️ژانر: #اکشن ، #تاریخی ، #جنگی #درام\n" +
                        "\uD83C\uDFA5سال انتشار :2013\n" +
                        "\uD83C\uDF96امتیاز :8.5/10\n" +
                        "\uD83D\uDC65بازیگران: Gustaf Skarsgård, Katheryn Winnick, Alexander Ludwig, Travis Fimmel, Clive Standen\n" +
                        "\uD83D\uDCCDمحصول: ایرلند , کانادا\n" +
                        "\uD83D\uDCDDخلاصه داستان: داستان سریال درباره فردی است به نام رگنار لاثبروک که با پادشاه آن دوران وایکینگ\u200Cها به مخالفت برمی\u200Cخیزد و دوست دارد این بار به جای نبرد با شرقی\u200Cهای منطقه خود، حمله به سمت غرب را در دستور کار خود قرار بدهند. رگنار با مخالفت با پادشاه، به کارش ادامه می\u200Cدهد تا راه را برای نبرد با غربی\u200Cها هموار کند و حتی خود را در مقام پادشاهی ببیند.\n" +
                        "\n" +
                        "\n" +
                        "\uD83C\uDD94 @TestJavaMovieBot");

        return sendImage;
    }

    private SendPhoto setGOTPoster(long chatId) {

        SendPhoto sendImage = new SendPhoto();
        sendImage.setChatId(chatId);
        sendImage.setPhoto("AgACAgQAAxkBAAIDn2EIOnRs8rOSUmJGjmipJlB5flSrAALftzEbzwVBUEhZ2VXURVcbAQADAgADeQADIAQ");
        sendImage.setCaption(
                "\uD83D\uDCA2نام سریال: Game of Thrones\n" +
                        "⭕️نام فارسی:  بازی تاج\u200C و تخت\n" +
                        "⚜️فصل: اول\n" +
                        "\uD83D\uDD31قسمت: فصل اول کامل\n" +
                        "⭐️ژانر: #ماجراجویی | #درام | #فانتزی\n" +
                        "\uD83C\uDFA5سال انتشار :2011\n" +
                        "\uD83C\uDF96امتیاز :9.3/10\n" +
                        "\uD83D\uDC65بازیگران:  peter dinklage, lena headey, emilia clarke, kit harington\n" +
                        "\uD83D\uDCCDمحصول:آمریکا\n" +
                        "\uD83D\uDCDDخلاصه داستان: در این سریال هفت خاندان اشرافی برای حاکمیت بر سرزمین افسانه ای «وستروس» در حال ستیز با یکدیگرند. خاندان «استارک»، «لنیستر» و «باراثیون» برجسته ترین آنها هستند. داستان از جایی شروع می شود که «رابرت باراثیون» پادشاه وستروس، از دوست قدیمی اش، «ادارد» ارباب خاندان استارک، تقاضا می کند که بعنوان مشاور پادشاه، برترین سمت دربار، به او خدمت کند. این در حالی است که مشاور قبلی به طرز مرموزی به قتل رسیده است، با این حال ادارد تقاضای پادشاه را می پذیرد و به سرزمین شاهی راهی می شود. خانواده ملکه، یعنی لنیستر ها در حال توطئه برای بدست آوردن قدرت هستند. \n" +
                        "\n" +
                        "\n" +
                        "\uD83C\uDD94 @TestJavaMovieBot");

        return sendImage;
    }

    private SendVideo setPeakyS1E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAID4WEIWbVYkh9j8c5fQ3C79aDUU0NYAAKrDAACB2qZU4_x2kzQyCVWIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل اول\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS2E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEDGEJBZwDxeTiHcrrKkczTeJxm5OAAAIeCgACwWygUzT_vAIM_4WVIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS3E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEDWEJBi8YWfFxkVLne45T2fLv5ab7AAJECgACwWygU1MQXA8iAuiPIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل سوم\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 480p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS1E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEMGEJCXV6Iafxa8txeBXB4_qSEcIuAAKuDAACB2qZU7ju2KSvUgqjIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل اول\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS2E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEMWEJCbyN8WyGKUmMIu8b4Yp7ElkaAAIlCgACwWygU67vI2sWH_HKIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS3E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEMmEJCe_dn_i3QSjZd6FQEq_rj-eqAAJJCgACwWygU6CXJ3K3uqH-IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل سوم\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS1E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIESmEJDX94d9yryeoCRSedwpoIa7FnAAKxDAACB2qZU6t-2YiwiZsIIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل اول\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS2E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIES2EJDg49zETabcyoG6Xj6ILfNnCXAAIqCgACwWygUy-bO9aJb9D1IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS3E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIETGEJDlvk0IubdpN4XHnACCGEJ-lLAAJPCgACwWygU0e_Y0QSdyB6IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل سوم\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS1E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEWWEJD7PIt1nJ9L37XZ054ECCJpb7AAK0DAACB2qZU8j5yq9sCoqNIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل اول\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS2E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEWmEJEDmiWjABUpE-mNIKUD4ZkWA7AAItCgACwWygU3OHWdFXdU75IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS3E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEW2EJEKkEZ5H1pRoyo5Gh_pcgsp3nAAJWCgACwWygU_WQokjGBp_pIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل سوم\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS1E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEXGEJEWvHQc9WmT3i3FRZPB68h9bkAAK3DAACB2qZUzhNn_LhYFOXIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل اول\n" +
                "✅قسمت پنجم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS2E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEXWEJEb0RhV8XOghtSFIVuPp_H0rlAAIwCgACwWygU9G-8qjguqFHIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت پنجم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS3E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEXmEJEe6znCCyTKYohS4FLn1zkiDJAAJZCgACwWygU-4VOKylwKYkIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل سوم\n" +
                "✅قسمت پنجم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS1E6(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEX2EJEoOwsUYNndD-y-DtUo5M_jEuAAK6DAACB2qZU_Zmh5nNGMHwIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل اول\n" +
                "✅قسمت ششم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS2E6(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEYGEJEstFbzcs-TamAuunRbk-SHK-AAI0CgACwWygU9KZDhgn37uGIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت ششم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setPeakyS3E6(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEYmEJExvJeZOfNxlo_Ibt0qXmIdqUAAJcCgACwWygUxLwzAE_FKNGIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال پیکی بلایندرز\n" +
                "⚜️فصل سوم\n" +
                "✅قسمت ششم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEkGEJVl62VOgYEq6HmC1IowkfAAH6kAACuggAAotD-VN0x1f0B5nUXyAE");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEkWEJVrZcMwffv72e1Y4v0-euMH3FAALACAACi0P5U9AKkz8vn_VSIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEkmEJVxGGSJw5epE-8DFZf1s_krnhAALQCAACi0P5U7z9IYIEkt1oIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEk2EJWT9GCydCM1cxUM2p1Yrkl2mEAALXCAACi0P5Ux8IAx1K67FeIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIElGEJWVc11vj79SV1-UmcBxui7yJ8AALfCAACi0P5U-QhDQb7IGZfIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت پنجم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E6(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIElWEJWZI-jw6oVBkm382Kxic2WL_-AALoCAACi0P5UxmQTLYluT1oIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت ششم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E7(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIElmEJWa-3dmkcYk0qWV5IamfF5aL6AALwCAACi0P5U2WRtYkPm0XaIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت هفتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E8(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEl2EJWdtw9zbs8lqnio1kyCmtK6LfAAL4CAACi0P5U-kKTL2dVRIAASAE");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت هشتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS1E9(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEmGEJWfVHZY4qEpWYAd0mdU6SMofiAAL9CAACi0P5U7XVb87WXltRIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل اول\n" +
                "✅قسمت نهم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEmWEJW0dTgwO7km_G4NpRL438wtorAAIJCQACi0P5U-JUJrH041U3IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEmmEJW2mwLzVPJFCEfY14MNh1JFhsAAIUCQACi0P5U1sON5CzdpS-IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEm2EJW4z_QgdbGZqUR9Yzlrsmet1wAAIeCQACi0P5UzHIVhbsMenRIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEnGEJW6y9IqbBxMNxFfBuyC7Ge9-0AAIjCQACi0P5U3VASETNtnSOIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEnWEJW9Eey6kaGZSpjR6_72suv4lBAAIqCQACi0P5U4W3vWSi7_cLIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت پنجم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E6(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEnmEJW_pZd4mtofdctTDpjB8FdrLuAAIwCQACi0P5U5dnevjys2kdIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت ششم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E7(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEn2EJXB4h_PJhZZPWbrOce7ND71UIAAI2CQACi0P5U6MDLGirS_L5IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت هفتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E8(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEoGEJXDXe08YD9IjJbH-5hVJPhKo1AAJvCAACi0MBULZeD81hmq8mIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت هشتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E9(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEoWEJXFqIsjDYBAXlpFLMV8s9v5imAAJ1CAACi0MBUATG5ZSf8fKEIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت نهم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setVikingsS2E10(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEomEJXJM-_L5JCZPuGUMHmCEIwrw0AAKACAACi0MBUBz2Gxn4tEyoIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال وایکینگ ها\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت دهم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIExWEJaYsyoT4dwTkOvLG5lxf_BLghAAIdCQACT0c5UKp15LZLBTtHIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIExmEJaYsR720KZgt92c3TOBYdMdydAAIhCQACT0c5UJUbfcKs8VAfIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEx2EJagObo1Ur4b_vV5VS9AWgXrLEAAInCQACT0c5UKt1fXOLk_pmIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEyGEJagPwK9A7SgTst5bvZbYH6W_uAAIuCQACT0c5UGTuJsiVZhWbIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94  @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEyWEJakmOEP5Qf9Ixy3yilKzUZ96KAAI8CQACT0c5UB86bdCVVf5YIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت پنجم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E6(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEymEJakkeG7TvjTXItfV0dp-2GUpcAAJCCQACT0c5UCQW_dDVgadmIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت ششم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E7(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEy2EJaoHW0fzDhtvWISXzUxke32pgAAJKCQACT0c5UDMxE_5166IsIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت هفتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E8(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEzGEJaoGmcUFs7X2rAkRHo0dB3eqoAAJNCQACT0c5UObVDhW6JUw9IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت هشتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E9(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEzWEJasYd9qx6vgsF68mLD-Dw9_WAAAJQCQACT0c5UL4TBUvDeh8-IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت نهم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS1E10(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIEzmEJasZiyZiWaEmc77x_0L0B7e9JAAJVCQACT0c5UDL74StVcm06IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل اول\n" +
                "✅قسمت دهم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE22EJbKb_dnPEYIpSn6FD6dOV6HupAAJZCQACT0c5UO_NbfV6dZbiIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE3GEJbKZI9ydUiD9D14oMHXsSB59vAAJeCQACT0c5UBcj7WNPjG5QIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIFLmEJcFkP4-PjdEqzl34sRz0_E4lTAAJhCQACT0c5UOag-kvDr_JFIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE3mEJbKbzzw-J0KgoQSLK9jaOxmaLAAJkCQACT0c5UBUUjnsMIWthIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE32EJbKYY5xSUVtJVDtX8l5mzyBkYAAJnCQACT0c5UB511Rnc3hlGIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت پنجم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E6(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE4GEJbKaV8nNjq4kzIG_IJswRKyyOAAJrCQACT0c5UIJFfuoscfaeIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت ششم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E7(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE4WEJbKbPp6rCemjvd7v6KgmB_-ZEAAJvCQACT0c5UBwSt1r3VfvxIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت هفتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E8(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE4mEJbKZQUJ9d0WxAxtqKVjIje64jAAJyCQACT0c5UDWVT3fYMZhmIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت هشتم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E9(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE42EJbKZOeQsqWKeou4_we21RGxd6AAJ2CQACT0c5UBbZPrPS9mZKIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت نهم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setGotS2E10(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIE5GEJbKYg6mij-Bg0EtxwrhzsYIZ7AAJ5CQACT0c5UFbEfAwGHdUkIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال بازی تاج و تخت\n" +
                "⚜️فصل دوم\n" +
                "✅قسمت دهم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setChernobylS1E1(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIFU2EJckruaH3Dn6wMiVdiBgQk6oSZAAKoBwACB2qRU6pm4FUcyLa1IAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال چرنوبیل\n" +
                "⚜️فصل اول\n" +
                "✅قسمت اول\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setChernobylS1E2(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIFVGEJckoh4AqHwq3Fe25wxbNLdJY2AAKrBwACB2qRUzrikXU3O3pYIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال چرنوبیل\n" +
                "⚜️فصل اول\n" +
                "✅قسمت دوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setChernobylS1E3(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIFVWEJckqfFQM8U87GrANlVcZRQyYIAAKuBwACB2qRU4ZzjTqQXgIjIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال چرنوبیل\n" +
                "⚜️فصل اول\n" +
                "✅قسمت سوم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setChernobylS1E4(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIFVmEJckq9RFdV9xd5CyjbkvnrxIh3AAKyBwACB2qRU-rQiqYe7-GxIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال چرنوبیل\n" +
                "⚜️فصل اول\n" +
                "✅قسمت چهارم\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setChernobylS1E5(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIFV2EJckrCIjmJDfYhOJ_LBdXB5mcVAAK1BwACB2qRU2EgSr4lyedtIAQ");
        sendMovie.setCaption("\uD83C\uDFA5سریال چرنوبیل\n" +
                "⚜️فصل اول\n" +
                "✅قسمت پنجم(پایان)\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendPhoto setFastPoster(long chatId){

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto("AgACAgQAAxkBAAIGAAFhGO61ltsGOrv5KBBXMTlMyVMEKwACX7YxG6plyVBH70TP1e_YIAEAAwIAA3kAAyAE");
        sendPhoto.setCaption(" فیلم : سریع و خشن 9 \n" +
                "\n" +
                "\uD83C\uDFA5  سال انتشار : 2021\n" +
                "\n" +
                "\uD83D\uDD05ژانر : #اکشن | #کمدی\n" +
                "\n" +
                "\uD83C\uDF96امتیاز : 7.9 از 10\n" +
                "\n" +
                "\uD83D\uDC64 کارگردان : Justin Lin\n" +
                "\n" +
                "\uD83D\uDC65بازیگران : Charlize Theron, Jim Parrack, John Cena\n" +
                "\n" +
                "\uD83D\uDCDD خلاصه داستان : داستان این فیلم پیرامون مقابله شخصیت دام و تیم همیشگی\u200Cاش با برادر بیگانه\u200Cاش با بازی جان سینا (John Cena) جریان دارد. ناگفته نماند که در این اثر شخصیت محبوب هان با بازی سانگ کانگ (Sung Kung) نیز مجددا به این فرنچایز بازخواهد گشت، اما هنوز چگونگی بازگشت او از مرگ مشخص نیست.\n" +
                "\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendPhoto;
    }

    private SendPhoto setCentralPoster(long chatId){

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto("AgACAgQAAxkBAAIGGGEY9M-RBTUUhhWF8_7wXlpSbn4IAAJjtjEbqmXJUAxoW7SUlCc1AQADAgADeQADIAQ");
        sendPhoto.setCaption("\u2060 فیلم : هوش مرکزی \n" +
                "\n" +
                "\uD83C\uDFA5  سال انتشار : 2016\n" +
                "\n" +
                "\u200F\uD83C\uDF96امتیاز : \u200F6.3 از 10 \n" +
                "\n" +
                "\uD83D\uDD05ژانر: #اکشن #کمدی #جنایی\n" +
                "\n" +
                "\uD83D\uDC64کارگردان: Rawson Marshall Thurber\n" +
                "\n" +
                "\uD83D\uDC65بازیگران: Dwayne Johnson, Kevin Hart, Amy Ryan,  Danielle Nicolet\n" +
                "\n" +
                "\uD83D\uDCDDخلاصه داستان:\n" +
                "وقتی (هارد) با دوست دوران مدرسه اش روبرو می شود متوجه می شود که دوستش (جانسون) برای سازمان جاسوسی سیا کار می کند، او همراه با دوستش تصمیم به جاسوسی از یک پرونده ی نظامی را می گیرند که …\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendPhoto;
    }

    private SendPhoto setIcePoster(long chatId){

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto("AgACAgQAAxkBAAIGJ2EY9pFVroCd0CnmnfJqkZnrmHmGAAJktjEbqmXJUIQvfFGMITQuAQADAgADeAADIAQ");
        sendPhoto.setCaption("فیلم : جاده یخی\n" +
                "\n" +
                "\uD83C\uDFA5  سال انتشار : 2021\n" +
                "\n" +
                "\uD83D\uDD05ژانر : #ماجراجویی، #درام، #هیجان_انگیز\n" +
                "\n" +
                "\uD83C\uDF96امتیاز: 6.1 از 10\n" +
                "\n" +
                "\uD83D\uDC64کارگردان: Jonathan Hensleigh\n" +
                "\n" +
                "\uD83D\uDC65ستارگان: Laurence Fishburne, Liam Neeson, Marcus Thomas\n" +
                "\n" +
                "\uD83D\uDCDD خلاصه داستان : پس از فرو ریختن یک معدن الماس در مناطق شمالی کانادا، یک راننده جاده\u200Cهای یخبندان مأموریت نجاتی غیرممکن را در سطح اقیانوسی یخ زده هدایت می\u200Cکند و با وجود احتمال ذوب شدن یخ\u200Cها و تهدیدهایی که آنها هرگز نمی\u200Cبینند، سعی می\u200Cکند جان معدنچیان گرفتار را نجات دهد...\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendPhoto;
    }

    private SendPhoto setTidesPoster(long chatId){

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto("AgACAgQAAxkBAAIGKWEY9u45rnEAAZgmYPoTFD5rzfPWPwACZbYxG6plyVC8rXSAncIjbgEAAwIAA3kAAyAE");
        sendPhoto.setCaption("فیلم : زیستگاه\n" +
                "\n" +
                "\uD83C\uDFA5  سال انتشار : 2021\n" +
                "\n" +
                "\u200F\uD83C\uDF96 امتیاز : \u200F7.3 از 10\n" +
                "\n" +
                "\uD83D\uDD05ژانر: #علمی_تخیلی #هیجان_انگیز\n" +
                "\n" +
                "\uD83D\uDC64کارگردان: Tim Fehlbaum\n" +
                "\n" +
                "\uD83D\uDC65 بازیگران: Nora Arnezeder, Sarah Sofie Boussnina,  Iain Glen, Sope Dirisu\n" +
                "\n" +
                "خلاصه داستان:\n" +
                "در آینده ای دور، یک فضانورد زن، که در سرزمینی فراموش شده سکونت داشت، باید درباره سرنوشت جمعیت باقیمانده زمین تصمیم بگیرد.\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendPhoto;
    }

    private SendVideo setFast480(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGRGEY-p-626SRVlZEYUCG7EpAoBJPAAK2CwACbZAZUH93URuqZxr8IAQ");
        sendMovie.setCaption("\uD83C\uDFA5 سریع و خشن 9\n" +
                "⚙️کیفیت 480p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setFast720(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGRWEY-q5zTfzkCGwMi22Z_SuHOTFeAAKTCQAClLgZUFIAAeRvpaiUSiAE");
        sendMovie.setCaption("\uD83C\uDFA5 سریع و خشن 9\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setCentral480(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGUGEY_VkvXMDLskqZ-TcmW9IgsgmyAAIkCgACqJARU1je7sdHNFh5IAQ");
        sendMovie.setCaption("\uD83C\uDFA5 هوش مرکزی 2016\n" +
                "⚙️کیفیت 480p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setCentral720(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGUWEY_WpuwHaWlQYqyYu4zU9CkGKzAAKNCQACrfURU3PmtCCUp23jIAQ");
        sendMovie.setCaption("\uD83C\uDFA5 هوش مرکزی 2016\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setIce480(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGa2EZARolNXngz__P6Ej6gypkxhsAAzIOAAIZ5LBSL08ENoXtSiYgBA");
        sendMovie.setCaption("\uD83C\uDFA5 جاده یخی 2021\n" +
                "⚙️کیفیت 480p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setIce720(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGbGEZASC25wwdAqR9hghEa9M2PEekAAIxDgACGeSwUg-Q50ypFk8aIAQ");
        sendMovie.setCaption("\uD83C\uDFA5 جاده یخی 2021\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setTides480(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGbWEZAlO-MiFEOd_WYwu8usQcMvd6AAK3CQACIXpBU6bAmuXkrsBzIAQ");
        sendMovie.setCaption("\uD83C\uDFA5  زیستگاه 2021\n" +
                "⚙️کیفیت 480p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }

    private SendVideo setTides720(long chatId){

        SendVideo sendMovie = new SendVideo();
        sendMovie.setChatId(chatId);
        sendMovie.setVideo("BAACAgQAAxkBAAIGbmEZAm4-k0QyPe-2s12brFWymigsAAI8CQACnyNAU-8mYXEWn1OkIAQ");
        sendMovie.setCaption("\uD83C\uDFA5  زیستگاه 2021\n" +
                "⚙️کیفیت 720p\n" +
                "\uD83D\uDCDDزیرنویس چسبیده\n" +
                "\n" +
                "\n" +
                "\uD83C\uDD94 @TestJavaMovieBot");

        return sendMovie;
    }



}