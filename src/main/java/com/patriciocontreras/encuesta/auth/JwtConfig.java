package com.patriciocontreras.encuesta.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEAv+bJDtUXrO49CKqnKZUQ9MBTb2Le31IHLsxsJFZGnD2DDkD4\r\n"
			+ "gIYdnuLA1KmfGxPHTr2CQwAOWMwR/4jGuC/+K28iiROWLI5o6sMBUdkFZt9PXPPe\r\n"
			+ "4AoSBvjqgzpkmr3rx6+jkPVGkvXVDMLaz4OMY7iEIzzF7WF9fTx8ZMdaRYYqZdMT\r\n"
			+ "YSz4eQzHWdf76xu6ba27EEANsVzLQIK9x+43g39bCQjHXGfsJIiWvgF+6vT4Aiuh\r\n"
			+ "HVtd9c0RLp+H8l5Zw365V8GK1EpAJL1O593LJFyrFj66slqk7lh1JalDNqnz/TmL\r\n"
			+ "tuECG4qW0jB0Ujkac5APcRLJG5xdDRpW+CHdMwIDAQABAoIBAQClppZQE2oIgO52\r\n"
			+ "mLkLj/2/CHX9ZJ92rtw8bYG8LC1vmTmUCEl6yEqxaJOD/5e0S/Jz6eqT42x/AklX\r\n"
			+ "B75iKCuDWr+5DAtKEvRGEmcFPA0JrCJZ3rm5M3g0RJJLDRDi8qahDOBotRFisopi\r\n"
			+ "JaG0F54MRq7prAA+1TT58kh+xgcKkzHpr+nl+/CJWck+/xRT0JAvrTn1aePJMNpH\r\n"
			+ "bzKjPePy2RhELU8BatJFqyGWtq/kp3TzGEc0FAc6TEqEKpEH5mUc/yhZbGDi7Ema\r\n"
			+ "DyKpjqUpDfnDGjcQW5ME1X6KtXtSdYlUbcxv0DsmS7PacYO1emO+RQWNrpk8FxFA\r\n"
			+ "mE9O1VeBAoGBAPEhO1fMcRrThbj4PQtRGZdtoq1C8+f3cawMSzbFMpQJVtYCgtOJ\r\n"
			+ "iSEjUE7I/YOaIFKiKxcvXwqlF/i5G22IDUuICgfo/ao4hAMaejszVj3024YwZ5Nr\r\n"
			+ "mWnKBUNTCJ65FpESDSlRv/OkR1HUD8ZXTKr2sDeGZqZG3TrDlUCz4rIDAoGBAMu8\r\n"
			+ "YC7kjNi3os+j6DcgnsNfVm+7y21NE0Wc1T+la7oi1c2IGZYjvIwxU1O7fWiKA5xC\r\n"
			+ "PSFE34MXRpF2BJh1UxQLnTb6Wv2glXjxdeac5umXdiBG+Da9rvO21Kwzl/HodQzl\r\n"
			+ "BeXoicGicD2Ggt3qbJoYGobwgAwe1Pm+LDG6u1kRAoGAOvqj5z/MUoF2bd3aIJB/\r\n"
			+ "r/hTGAoOWqi9lxWSuBzX2DnIkaQfl9pGPmLC2+Jxj+T9Dvk3jP47QwFKsb2lJc9n\r\n"
			+ "YRgvS+wDT8VvFMs/JofSCml/4HyqePaSmbueOe9vZlNjX6yIp5ilWNTP1QYHrb3g\r\n"
			+ "tNiOFaa8KOOgbSU1tpPJWT0CgYBTUEVE59FxDidWIgDkCkLiQipmprUZjVtELE5I\r\n"
			+ "KK1VAMnNsspDy3lfGwyoQROXnFi9nZjB8hg001/YPBrE/bVrjRRNVzO+Y9y8CIHe\r\n"
			+ "PGtxOq7jpCH8wLS6lRmDEdYg5p53F3DCA8XRmUus3UTG2wZuPU0bwV2bl0AgoNQR\r\n"
			+ "wfq7AQKBgQDNQx9Gec6BZEu4IwvDO20PP8IlTXlxOzJUL522YsP/nFnnGiSahgI5\r\n"
			+ "JQPR4ZEeVCW9fBJsLXYm+ylnj65aFhGdYotAREDj5l5z6zNf7BwCWCTyqliuXYpc\r\n"
			+ "m/2JZWOOdwValnEV9qPO9yoPCKLxcZpmyAX2ec8791WLe6AJRQxWwA==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	

	public static final String RSA_PUBLICA ="-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv+bJDtUXrO49CKqnKZUQ\r\n"
			+ "9MBTb2Le31IHLsxsJFZGnD2DDkD4gIYdnuLA1KmfGxPHTr2CQwAOWMwR/4jGuC/+\r\n"
			+ "K28iiROWLI5o6sMBUdkFZt9PXPPe4AoSBvjqgzpkmr3rx6+jkPVGkvXVDMLaz4OM\r\n"
			+ "Y7iEIzzF7WF9fTx8ZMdaRYYqZdMTYSz4eQzHWdf76xu6ba27EEANsVzLQIK9x+43\r\n"
			+ "g39bCQjHXGfsJIiWvgF+6vT4AiuhHVtd9c0RLp+H8l5Zw365V8GK1EpAJL1O593L\r\n"
			+ "JFyrFj66slqk7lh1JalDNqnz/TmLtuECG4qW0jB0Ujkac5APcRLJG5xdDRpW+CHd\r\n"
			+ "MwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
