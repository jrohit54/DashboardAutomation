package Dataprovider_Component;

import org.testng.annotations.DataProvider;

/**
 * Created by rohit on 19/2/18.
 */
public class DataProviderClass {

    @DataProvider(name = "PublisherDetails")
    public static Object[][] pubDetails() {
        Object[][] data = new Object[1][8];

        //1st row
        data[0][0] = "12345";
        data[0][1] = "testemail@gmail.com";
        data[0][2] = "testName";
        data[0][3] = "testComanyName";
        data[0][4] = "testFirstName";
        data[0][5] = "testLastName";
        data[0][6] = "maps.google.com";
        data[0][7] = "category1 , category 2";

        //2nd row
        /*data[1][0] = "poiuy";
        data[1][1] = "testEmail@gmail.com";
        data[1][2] = "testName2";
        data[1][3] = "testComanyName2";
        data[1][4] = "testFirstName2";
        data[1][5] = "testLastName2";
        data[1][6] = "maps.google.com";
        data[1][7] = "category1 , category 2";*/
        return data;
    }


    @DataProvider(name = "AdvDomainDetails")
    public static Object[][] advDomainDetails() {
        Object[][] data = new Object[2][1];

        //1st row
        data[0][0] = "maps.google.com";

        //2nd row
        data[1][0] = "http://maps.google.com";

        return data;
    }

    @DataProvider(name="InvalidPubId")
    public static Object[][] getInvalidPubId()
    {
        Object[][] data = new Object[2][1];

        //1st row
        data[0][0] = "";
        //2nd row
        data[1][0] = "!#!@#!@#@!#@!#@!3";

        return data;
    }

    @DataProvider(name="InvalidEmailAddressPart1")
    public static Object[][] getInvalidEmailAddress()
    {
        Object[][] data = new Object[12][1];

        //1st row
        data[0][0]="plainaddress";
        //2nd row
        data[1][0]="#@%^%#$@#$@#.com";
        data[2][0]="@domain.com";
        data[3][0]="Joe Smith <email@domain.com>";
        data[4][0]="email.domain.com";
        data[5][0]="email@domain@domain.com";
        data[6][0]=".email@domain.com";
        data[7][0]="email.@domain.com";
        data[8][0]="email..email@domain.com";
        data[9][0]="email@domain.com (Joe Smith)";
        data[10][0]="email@domain";
        data[11][0]="email@domain..com";

        return data;
    }

    @DataProvider(name="InvalidEmailAddressPart2")
    public static Object[][] getInvalidEmailData()
    {
        Object[][] data = new Object[2][1];

        data[0][0]="あいうえお@domain.com";
        data[1][0]="email@-domain.com";

        return data;
    }

    @DataProvider(name="InvalidDomain")
    public static Object[][] getInvalidDomain()
    {
        Object[][] data = new Object[3][1];
        //1st row
        data[0][0] = "asdfgh";
        //2nd row
        data[1][0] = "adc@snapdeal.com";
        data[2][0] = ".com";
        return data;
    }

    @DataProvider(name="BidderDetails")
    public static Object[][] getBidderDetails()
    {
        Object[][] data = new Object[1][2];
        //1st row
        data[0][0] = "1";
        data[0][1] = "testBidderName";

        return data;
    }

    @DataProvider(name="validFeatureMappingDelatils")
    public static  Object[][] getFeatureDetails()
    {
        Object[][] data = new Object[1][2];
        data[0][0]="FULL_URL";
        data[0][1]="{\"ALL\":10,\"8CUXBGVW2\":90}";
        return data;
    }

    @DataProvider(name="invalidFeatureMappingDetails")
    public static Object[][] getFeature()
    {
        Object[][] data = new Object[4][2];
        data[0][0]="MODIFY_FLOOR_PRICE";
        data[0][1]="100";

        data[1][0]="LOG_SAMPLING";
        data[1][1]="101";

        data[2][0]="FULL_URL";
        data[2][1]="snapdeal.com";

        data[3][0]="FULL_URL";
        data[3][1]="test.com";
        return data;

    }

    @DataProvider(name = "PartnerDetails")
    public static Object[][] ptrDetails() {
        Object[][] data = new Object[1][8];

        //1st row
        data[0][0] = "TEST123";
        data[0][1] = "TESTPARTNER";
        data[0][2] = "TESTPARENT";
        data[0][3] = "testemail@gmail.com";
        //data[0][4] = "Active";
        data[0][4] = "1";
        data[0][5] = "testApiKey";
        data[0][6] = "Testpasword";
        data[0][7] = "123";

        return data;
    }
    @DataProvider(name="InvalidPtrId")
    public static Object[][] getInvalidPtrId()
    {
        Object[][] data = new Object[2][1];

        //1st row
        data[0][0] = "";
        //2nd row
        data[1][0] = "!#!@#!@#@!#@!#@!3";

        return data;
    }

    @DataProvider(name = "ExchangeDetails")
    public static Object[][] excDetails() {
        Object[][] data = new Object[1][8];

        //1st row
        data[0][0] = "12345";
        data[0][1] = "testemail@gmail.com";
        data[0][2] = "testName";
        data[0][3] = "testComanyName";
        data[0][4] = "testFirstName";
        data[0][5] = "testLastName";
        data[0][6] = "maps.google.com";
        data[0][7] = "category1 , category 2";

        return data;
    }


}