package project.bookplanet;

import java.util.List;

import project.bookplanet.bgjobs.WebpageDownloaderTask;
import project.bookplanet.entities.Bookmark;
import project.bookplanet.entities.User;
import project.bookplanet.managers.BookmarkManager;
import project.bookplanet.managers.UserManager;

public class Launch {
	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	
	private static void loadData() {
		System.out.println("1. Loading data ...");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
		
		System.out.println("printing data ...");
		printUserData();
		printBookmarkData();	
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}	
	}
	
	private static void printBookmarkData() {
		for (List<Bookmark> bookmarklist : bookmarks) {
			for (Bookmark bookmark : bookmarklist) {
				System.out.println(bookmark);
			}
		}
	}
	
	private static void start() {
		//System.out.println("\n2. Bookmarking ..."); 	
		for (User user : users) {
			View.browse(user,  bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		start();
		
		//Background jobs
		//runDownloaderJob();
	}
	
	private static void runDownloaderJob() {
		WebpageDownloaderTask task = new WebpageDownloaderTask(true);
		(new Thread(task)).start();
	}
	
	/*public Launch() {
	 	loadData();
	 	start();
	 */

}
