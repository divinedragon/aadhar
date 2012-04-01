package com.ignou.aadhar.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddressDaoTest.class, BankDaoTest.class, CitizenDaoTest.class,
        CityDaoTest.class, DistrictDaoTest.class, ServiceProviderDaoTest.class,
        StateDaoTest.class, TransactionTokenDaoTest.class })
public class AllTestsDao {

}
