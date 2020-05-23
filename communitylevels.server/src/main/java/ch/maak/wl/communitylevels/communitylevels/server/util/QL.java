package ch.maak.wl.communitylevels.communitylevels.server.util;

public class QL {

	public static String and(String... queries) {
		if (queries.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String query : queries) {
			sb.append(String.format(" AND ( %s ) ", query));
		}
		return sb.toString();
	}

	public static String or(String... queries) {
		if (queries.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < queries.length; i++) {
			if (i > 0) {
				sb.append(String.format(" OR ( %s ) ", queries[i]));
			} else {
				sb.append(queries[i]);
			}
		}
		return sb.toString();
	}

	public static String ge(String left, String right) {
		return String.format(" ( %s >= %s ) ", left, right);
	}

	public static String le(String left, String right) {
		return String.format(" ( %s <= %s ) ", left, right);
	}

	public static String eq(String left, String right) {
		return String.format(" ( %s = %s ) ", left, right);
	}

	public static String like(String left, String right) {
		return String.format(" %s LIKE %s ", upper(left), concat("'%'", upper(right), "'%'"));
	}

	public static String upper(String query) {
		return String.format(" UPPER( %s )", query);
	}

	public static String concat(String... params) {
		if (params.length == 0) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(" CONCAT (''");
		for (String param : params) {
			sb.append(String.format(", %s ", param));
		}

		sb.append(" ) ");
		return sb.toString();
	}

	public static String groupBy(String query) {
		return String.format(" GROUP BY %s ", query);
	}
}
