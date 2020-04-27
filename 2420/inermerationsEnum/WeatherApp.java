package inermerationsEnum;

public class WeatherApp {

	public static void main(String[] args) {
		
		Weather favoriteWeather = Weather.CLOUDY;
		System.out.println("Favorite Weather: " + favoriteWeather);
		
		for(Weather el: Weather.values()) {
			System.out.print(el + " ");
		}
	}
}