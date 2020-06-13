package ch.maak.wl.communitylevels.communitylevels.server;

public class AbstractDataService {

	protected String addPageBind(String column) {
		return ":{page." + column + "}, ";
	}

	protected String addPageBindLast(String column) {
		return ":{page." + column + "} ";
	}

}
