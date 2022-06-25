package fernandezsanjuan.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import fernandezsanjuan.framework.Menu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu framework = new Menu("/fernandezsanjuan/utilizacion/config.properties");
		framework.run();
	}

}
