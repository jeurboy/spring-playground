package com.playground.employee;

class CompanyNotFoundException extends RuntimeException {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	CompanyNotFoundException(Long id) {
        super("Could not find company " + id);
    }
}