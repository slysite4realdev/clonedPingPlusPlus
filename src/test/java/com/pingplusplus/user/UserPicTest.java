package com.pingplusplus.user;

import com.pingplusplus.PingppTestBase;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.UserPic;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/*
 * @author Afon, @date 19-07-12
 */
public class UserPicTest extends PingppTestBase {

    /**
     * 证件上传
     */
    @Test public void testUploadPic() throws RateLimitException,
            APIException, ChannelException, InvalidRequestException,
            APIConnectionException, AuthenticationException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", "test_user_001"); // 用户 ID，首字母必须是英文数字或者 _-@, 必传
        params.put("type", "customer");    // 用户类型，customer: 对私，business: 对公
//        params.put("acc_no", "2019057929311601000631"); // 壹账通用户编号。覆盖的时候使用，新用户不需要该字段
        params.put("operate_type", "00");  // 操作类型，00: 新增，01: 修改，02: 增开户。不传默认为新增
        // 图片内容，base64 编码
        params.put("pic", "/9j/4AAQSkZJRgABAQAASABIAAD/2wCEAAEBAQEBAQIBAQIDAgICAwQDAwMDBAUEBAQEBAUGBQUFBQUFBgYGBgYGBgYHBwcHBwcICAgICAkJCQkJCQkJCQkBAQEBAgICBAICBAkGBQYJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCQkJCf/CABEIARsBkAMBIgACEQEDEQH/xAAfAAEAAgEFAQEBAAAAAAAAAAAACQoIAQIDBAcFBgv/2gAIAQEAAAAAvQPi/jPifD+dw8LTk1110373Nv5ubn5ubsc3Lz7+Tk1113bt27fv37t+u7j/AGGpULyA+H+P6u3aajXVru137+59Tt9vm5OTl37+Tfu37tePY5+fd2UdVkyRorGWcdwAAAAAAEG+RUnxWLs5bgAAAAAAIM8iJRCsLZ43Brjngj6bI39EAAAAAgyyJlEKvloHcMLq42ZkhHlkXHtlmHeAAAABBlkTKIVcrRe4xBgTtTcgwfgDtuAAAAAQZ5ESiFWm0puKqNq4CHjqzIgAAAAg0yIlDKrtqDcxriwnjAVK7agAAAAINcipQCqxal3IRJF8pAFTi2LuAAAACDbIqT8qu2oNyIf3eQEBU8tg7gAAAAg2yKk/KrdqLc/KVqLQQH5erTa+AAAABBxkTJ8VWLUu516Xlg303tdrt9jsVrbF2cwAAAAIOMipPSqrao3PBKymE8lUhvJyfLq8z3eEWogAAAAQc5FydlU61fueCxfzdQJYafQ6fyrF2TVaeyyAAAACEXICToqiWudzxKpFIb0vnfP+dwdfT49qQAAAAEJ/u8nJU4tjanKNNumjdtAAAABC97XJsVLLaWoAAAAAAENXsEmpUdtw6gAAAAAAQ3+vSZFQ+3gAAAAAAAhz9dkvKgdvwAAAAAABDv67JWU9bhQAeVfS9C+J9weU+aYYS4DzToetYw5E/VbtoAh59ekoKctxvQAwIjd/eSVYdesZ9YeZ04J19LeHOMMoFLXFJySjJ2CT9rNBNLoAh89eklKaNy/QA/K138wvOMAcusjoyZcO15/LB+hCjxlVBLhB15BP6H1WOa+F2SOcgEP3sEkJTDue6AGsA35GwVVhyHzcq3SBfgcnLNf3RhdQS/ol0dZUrSFTCz1Rvmvkmi1s6gh+9kkdKWd03QAdbt7cPYwJt8IJB4WMrflSYiF3O75VYy57pT1uFUbLuX8/a1h1JhQh99pkZKV91HQAAGzeAGlP63/v6sGH7qWP94a6Q9+4SKFKm6voAAaa7PKPW2LOTXbfIjZk9dH8JEdKj7SHhGKkkNVS1XDt7zIiUpbregBjH+z9h6kS8jOBcq1bToWX66vx5rIbuSwDiRiv6pzxmWWYQZucbo+5lUFXuUsFO24nDv75IcUnrsWgBjbW58s/Z5hS24sS41q+ayjWu+vY66bzKFCUmMb9T+HnmhumuhA4pwt0LXemUp33Eoeve5Cyk9di0AMcqqkun7nDmffzfAOSr7WFPr2ZvKYT4OZM/mvn4FTD+P5e/uPJ/wANzZE4n5xeE+7Q/wDushZSeuxaADTV5H6401a6ADTUNWgQ+e9SElKu6poAAAamo10NdGug00Q9e3yLkCHnOnz/AJHy+r0ui2cfDwcXF1+n0un8z43U6nV6vW6/U4ePr6cfDu007HP3/t/f/b/d/FWbsiwAAAAAAAAAAAAAAAA//8QAFwEBAQEBAAAAAAAAAAAAAAAAAAECBf/aAAgBAhAAAADn6lAQAok1kAAAqayqyAAFTWaioAAqWWCwABUs1kWAAKllsWQABUstayyAAmpYAAALLAAABZYALAAsAtS5NEIWWAtMqsELNZAALAmprIahUlsJrNTWQqWCiCpqLAKAIQAAAD//xAAWAQEBAQAAAAAAAAAAAAAAAAAAAQT/2gAIAQMQAAAA0SpZUoAgqUAAAlSgAAAlAAAAAlAAAlAAAAligAAJUKAAAAAAAAAABKAAAAAAJQEoAAAAAAAAAJQAJQAAASpQEUhQAAAA/8QAOBAAAQMDAgMFBwQCAAcAAAAABgQFBwABAwIIGCA3ERUWFzgQEhMwMUBBFCEyUCg2IiQlJjRRYP/aAAgBAQABCAL2Z1KdNp99Td6ZtP8ALUSDuj+dy0U0/wArmYfb6+NQ2vG4ZXjcMrxwF142DK8bBteNg2vGobXjYNrxqHV40D68Zh9eMRGvGIlevFwnXiwVrxUL14oGa8TDd6sRj1/p4hH/AMd/sNd+sdd+Mld9M1d8s9d8s9d8NFd8NFd7tNd7tNd7NVd6tdd6Nld5ttd5Ntd5N1d4t9d4N9fr0Fd4t/LOI+iMZoEBN5vtNjC/017SI11fxvtBj/t/bhAA64QASuEAFrhABa4QAWuEAErhBBa4QAWuEAFrhABK4QAOuEEErhAA64QAKuEABrhAAK4QY/rTtCjy314RY57a4R42rhJjSuEqM64S4yrhMjKuEyMa4TYwrhNjCuE6L64T4trhOiyuE+K64ToprhOieuEyJfzwlRF+eEqIa4S4frhMh6uE2Hq4TodrhOh2pV29RiDg6omZogU5lkWD6lT7ZH9RYH/W7jek6y1Qr0kHeSR/UWB/1u43pSrqE+kg7ySP6jQT5D4Xiozo+IQ5J6iPHq7LN0yRY65bJ0eLNiz47ZsH9DuN6UqqhPpIO8kj+o4E5jg/Go9aLu5HodZxm+3xGBi2vx4g1XVP+KF4mw6Pc0ucAxG56L6bqYALwfJ3lDYZPefC8WC5atft/e33+43pSqqE+kg7ySRf/JAFtynRqzR+NZyV7j2L3Y6efNSXLWta3ZbkPI+HJEZ7tL/GhaQxYWWh+Rfv9xvShXUJ9JB3kkj1JA3Ll0ec825EmfnmyPMZ+HZNKWCz3KfAeFSv++3G9KFdQn0kHeSSPUoD8hk/aBcTciLXtvH9TNFiNwU/IjzR4G3Dv4Xh++3G9KFdQp0kHeSR/UoEcm41VrSQy968YOlxIQxpR4fkHP8AyO6QUUYfvtxvSdZUKdJB3kkb1IhfJPTdd0iB+wWil2s9xqxudvkPXY/7r2tPj++3GdJ1lQp0kHeSRfUiGci5FgckOZuVbbnDOxYXiJXfnWrUrajyuC7bymVFhMQy64/fbjOk62oV6SDvJIvqRDeTVq06NN9euWSUaaTJBLUctczxe6IsKu1pSjW/0tJ8cX+lpLju/wBLSPH1/wBrWkUBv9PMIDqc5NSlmtPGIXHicRahrAOB/wB9uN6TLahXpKO8kiepIP5Cv/V3LthKHo1KYwan1/keFNMevHi5nAw7bdIiO2Zg8gIdryBh2vIOHqUQbCyPBqVKzJNGpO52BoUC9twIzMehOWww0tzBMxwxs3324zpKuqFeko7ySJf/ACTEOQq/1hxrbl0ZZfYa7egsnW9+MtmvdCFduJt82dwCa3uK7m251/v8Bp0QOfHGfSql8VDRkKbu7Bmox6+SB9/uJ0+9Ey/thbpKO8ki3/yTEeQlw5VA44J8EUTKiBABuFXW+5RiriWZK4mWar7nGjTf9uJ9priiaK4pGeuKRnqDF6kjk0vM/v8AcP0mcKhbpKO8kjX/AMlBHl7b126q7b123rtvXbf+j3B9J3GoX6SjvJJHqUEv63cB0ncqhfpMO8klepUT/rZ+t2xO51DHSYd5JM9Son/Wz70ndKhnpOO8km+pYU/rZ96TOtQ10nHeST/23LivzMT21KWvI8omh0SvjSmekFJlKZZhspScj653ZGVW8WbzFgXiOE3uAGBWfOeYjxcjy8Ng+153p5Yn1pJmjA+sVFBmLhabGrJ8GfCqw6FKb5s+dJnWob6TjvJJ/wC25oW+XJrm9s4UuXsUSYEyIcuBZB1gZ4pTtSsoPHzCNhTq+5w3JgjSE0K12wyU2eKkQw4LcmfEjy5UsZlmY4A2wpU0cn4kEIf+444jB6e2JtbJQ06dOjTbRo5JBFNRuILBrHARk5s7pnhoqrdUp1+I2NNp23v+q8Z68Ts/T/FzF26NBNumfVWjVjFIjbphMTFGbEJfLWQQkhrDF3yZ86TO1Q50oHOST7/5OjGn5a7Uo0IsupJkKpoObqhxgEhwjZk2kLeZ1SvTiytbWglGSSR/FtQYk0g8qEvd2AtdZoHkS5Q0pYPLSFuQKRzMES8Kna7u1uKIcADF6uQvqOGYhFs2l8ukWJXBLjXoeUeIG4lnB0k93eNyTs9OGtji2QsEpPpO3YJBIBUhHFGNtLlefCvS49TLGxZFgkvxqiphfGslZ078y7psX6JYNEeksmUADsl0rrn3XJVd9WkXiWY8kjr1rM5c099JXeod6UDnJKF7cTwzb5hUWhsVEt8beKSA5XVKHNbrz2wprqcyhiLZIdtUvZI2MJDJDpS25pWM3Aea8A8Mt2kwj1IcgGrCNOiSEhM2ajuXFxkWsYzG4IFx4eq1eopTJkyJPoRo+SRiC4qCOz/oiOLV8iLu7Ljw2xCbZpaR+cTVuJZFbVIUF7dtStXcmllMmTok+lIj3MrlTi4sUfNQ2PNwmxJh1p3ImVnw1sPpIz29jLM3YXY0kIzZYtFdbni22CK5M1rJCeuae+kjxUPdKRzklD1QDfzL6NF7+9f2HAhjOmO48ryx8Wxsuu6xA26865AmXuSUD941IH92SJEyBJjQopCb0djMMaG4wi4aMVuh6yjQaZML1oVL+SdBMuNRHEwiQOIoAcYSjbfITE7E4U5MDECbaX9G/JHcu9hre7hunYUWq3/umqHStXNOvK/LFeFElyrlTW/scuyLqKZLXzpErVi93FHE0D0mu61qZeWfOkbzUQdKhzklD1QDf2nZb6/Nd9WnHu0Q3y2+ns16NGXRfHkW7cIqV5759Dbt+iluv72tua2xnTWRNPLPvSJ6qIelQ5ySl6oBv7O97W/e7q/MrHdPpefYYGTCCs/fhFo16cmi2TT7FGfClwa1SkYmWNy5fZrZvZly48OPVmzNDu2v7ZheGcohvETSegkDK2EbG9LFaBq5ScibxIeWEjpFp/lkcb1P2anUpJdW5xCwJan/APaHny9RF0rHOSUfU+OfLIjQSEfhWKGIjHydJ+vHdWrTo03168ObCp0fETg8iOZscv7YjIXLUzMC130Qm7kBBGbY+E9bk82ZQ1Do1W5HFpxoxL3dX8r0KubiQ7hiS2fcpf8A6Aw6aMCbAHsCgiU8TUf/AJ4mY/v/ABw5catNozWL4+Fjdo7neYWInZ6F1DW/GEmNos5Yh1CJnDPIOJewrtaN+ghYks22/bVUCaNHg9bn0U7FTUyvTYwLX7Pm0TiO4dHs3H6uyKlOmoK0W0REwdl/pWT1X6fZuA6OvtRH0rHOSUvU+OfLIA4TK/hXJl7ePxVPTNjF1bwzT9KWQRyaBJsiOZxphj3bT/rD12yJr+GAvWuoH0+7EDB7NyGnVgwijvW5X/wBbXbV9b0NXxMm5ghbde5LDlyMTBqx3+te5pr3dNPT6zDjfrdX5dIpjINtTTEQMGIAMcxD7evSJ8m4tBpwq/hW3Eov0Us6kiQ+EHUoU5PhJsma+3nHrtEzcqyVI3VEDoty/pZyEMmv2bhEeRXEzlrx7f1+FbE7VhxX+lZr2x7r9Hv1P/R1+qJOlg5ySl6nxz5ZcrIUI0sViYvApWbvGQrmAxhdO5Z211AQCJMYo9ZDAjhK1mA7NgjW/NGF/Y1jGoChjEGCiEWwtiU4b5rclCoiGWEtbLsxG6MLI+Y8OF4lcmJxEXs+C2oWHSF8a5AzXta/19ptHwzIKXAkJccAhODHpw4BeLh8Rde9203AnZ7fEJiJCAK7C+V0LHkhc5YPh3OKrmRkdGUCTjqqH2hwYYyZmd2bnnvBxXt9KG9ArUYFap3Fmh7dW15Xnr2+DgkseRsDe3sjEkT0RuLeidkGZrcQOPmCOmzM1sFahgd1v+kq11PvR1+qJOlg5ySj6nx35+NgZcL1kI8X3s/dHX6ok6WDnJKOjt3Pjf8AWz7/AMUQvWiogv70VDl+STokfi4wbjUV8DbiK8DbiKuCbi/xcB3H/jwBuU/F4/3K/i8f7l/xePtzNeX25f8APl9uUry93J15e7k68vNydeXm5OvLvcnXl3uUry83LV5e7ma8vdzdXj/dB+PAG6avL7dLXl5ukq8d7o71eOd0V6vG+6G9eWu6CvLPc/V4y3P3ryx3PV5X7na8r9ztXi7c7evK3c5Xlbudryt3OV5WbnKtF2521eWG56rRnuftWmON0Wn6aY+3S2rRH25y/wDK0eblvy6xFPz+g1tL4ID+gTFm8Zx//S//xABaEAABAgMDAwsMDggFAgcAAAABAgMABBEFEiETMUEGFCAiUVJUYZPR0xAjMDJCcZGSlKGxshUzQFBTVWKBo6SztNLkQ2NygqLC1OEWNYPBw0TEJWBkc4Tw8f/aAAgBAQAJPwLqOJbHyiB6YnGOUTzxPyw/1Uc8WnK8sjni1ZTlkc8WtJ8ujni15Pl0c8WvJ8ujni15Pl0c8WtJ8ujni1pPl0c8WtJ8ujni1pPl0c8WtJ8ujni1pPlkc8WrJ8sjni1ZPlkc8WpKcsjni1JTlkc8WnKcsjni05Xlkc8WlK8qjni0ZblUc8WhLcqjnifl+VRzxPS/Ko54nZflU88TrHKp54nGOUTzxOMconnibY5RPPE2xyieeJtnlE88TbPKJ54mmeUTzxNM8onniaZ5RPPEyzyieeJlrx088TLXjp54mGvHTzxMNeOnniYa8dPPD7fjiH2/HETDXjp2NVSc4zMJUBnHdVTXMdqIXND52ujiYm08h0UT86P3ZboYtGd8SW6KLRnPEluii0ZzxJbootGc8SW6KLSnPEluii0ZzxJbootGc5OW6KLRnOTluii0ZzxJbootGc8SW6KLRnPEluii0ZzxJbootGd8SW6KLRnfEluii0J3xZboYtCd8WW6GLQnvFluhienT80v0MTc54GOiiZnPoOih+b+h6KHpv6Hoodm/oejh2a+h6OHJr6Lo4XNfRdHCprwtdHBmvC3+CDM+Fv8Ea58ZH4I1z4yPwQJnx0/hhMz44/DCJrlf7Q3Nct/aGprljDM1y5hiZ5cwxM8uYlpnl1RLTPLqhh0TLCmUoyjhWnrjiUYg9+FlxapBmqlYk7XYbya9VXvbwiT+8IjgLXo2G8mvVV728Jk/vCI4E3sN5M+qrsFpS0kP1ziUxbTS+NAUoeECLek8orMlblw/wAVIUFoOZSTUe8XCZP7wiOBN7D4OZ9VWyeuA1ybScXHCNCR6TmGmKambBczPfpHE8RG3XX5NwbilQqZtaZXitbrhQD3wil4ftlRjU7IHvsg+mLGalie6lipk/wERbz0uUGus5k0QriqBc8ZBUd9En7DWoMzp2rS6mgqKm7XQoFSDvq4e8PCZP7wiOBN7D4OY9VWxPW2sEoHbOLPaoHf3cwGJhOWdmKLlJBfaNN50XknQO5R+8vbUCdGxaxFck8mmUaJ0pPpScDph2/LL/y2bOahNEpqe4VmSDihW0zFHvBwmT+8IjgTew+Df9VWx65qf1IZx3L04T4DdI74pTMo9gR/4jI1elVZjUds3XcWPAaHOIVen5I62mScCpSQClf76CCePD3fwmT+8IjgLew+De9Cth/0Us4786Rh54201a6lzzy9Ki6dqT+7TsIuytqNGbaToz5Qedbg7yR7v4TJ/eERwFrYfBu+hew7pDaPmU4BAohuTZSKfsDsOeblyhfeQl4fze7+ESf3hEcBa2G8X6rmwxuS2V5IhX+0Z3JNqv7SRQ+fsJqLIksoeI3HLw+kR7v4RJ/eERwFr0bDeK9V3YCrb6FNqHEoUMGk1YU0st1zqZWa18O24godgWG2WEFxajoSkVMN3fZB4sS9c4TnV4EhtB40H3fwiT+8IjgLXo2G9Pqu7A0AxJMWrJTtoS9GZuVZfSVPN5tGjuVHRtVdzSLck2FOpvZF51KHUbqVJOII0xb9n8ujni3pDl0Rbkjy6OeLbkeXRzxbUly6OeLakuXRzxNsuNzi069m8oMihNahBXmp3TnELvdRNszbFnpDa1NLSs3ztipd3ulnbe7+ESf3hEcBa9Gw3v8AK9sOCveoYslqZm3kKvuKKqm6ogadwRZYt2wAavSLl4qZB3Cmqro7lWN3MRTFNlS+XABXLOEh1FeK9iPlJqDFgy/8XPFgy/8AFzxYMv5+eLElGm0CqlrqAO+SY1NsTs49gqeSDdbG+brhT9arajubxwiVbtaeVtnHF1uJ+Q2NCR4TnhlMvKSolEtNIzJFCr0k+7/h5P7y3HAWvRsN7/s9sOCu+oY3i/XPUK7EtOpWH5TapKjnJRunSpN1XHE1Kao5ZGbK+2kbm2KCPnWqNQJUrdS9h5qxqXl7Jr+kmFBY86x6DGqFb7Na60ljVPerRKB8zd75USiJVs4qpitZ3y1HFR4z1P8A0fq+7/hpT7wiOAtejYb0eh/YC8tcs6lIGklBiw7XcmJVKgstSpu4qrppGp+2/JY1O235N/eNTdt+Tf3jUzbnk4541MW55On8UamLc5BP4o1M23yCfxRqZtvkE/iiSmZOWtJMqpsTLZQapBBTuE4aK+7/AIWV+3RHAGvRsN6n0P7EwYMGD7yb+W+3RHAGvRsN6j/n97d9L/bIjgDPo2G9b/5/e3dY+2RHAGfRsN61/wA/vb+p+2RHAGfV2G9Z9L/vb+p+1RHxez6uw3rHpf7I8mYlmw5VbRvDrVQsYaQQR34vZCbaS83eF03VioqDm6jiXW1ZlINQfnGxZXM61aU7km6Xl3RWgrph8M2e7LiZyjmASgiu270SqZLU0UlMnlUnXMyfhvkN7g07F0MSssm+4tWYCHhMSkym824nSOpOIlEPKuIvaTpzbgxJ0QoLbcAUlScQQcxHZv1X2qY+L2PV2G9l/TMdjyQcQ0ordeNEtN3Tecp3RAzCHLT17rEJUmcYUlhsXKdaWEhFNtu3jE/NTsy7SQ1zfXrRmo2qcnW4hOAQlRFa6cYO1lpV1eGet3ACGiE2XZoffbb7aoTfUBWgqTDDkp7KSyZiSecpceJFVN8S07mmAFOJQopBzVAwhKUvTbIU4EdqFjBVK1wr1Jjrj6SGpZvbvvaKIbGJz580Phqx7OWdY2SpSQt3bVQqaumij8jw7kCgGAA2L2t1zABQ5oC0G8mvyajHihvJTVn39b/u0K2691goKQrSk44jqKICZWYX85cbEOBtiz5pxlpxxVBk8F0BOhF67xUi0NfuDC7JpL2O4VjaD5zFntyCKe3TZyix+4khA8eJ6dRZsuoqJfVk0vgpIuIYF0FNTW8U94nRIjWVoJRWbK8QpxRQLqaYgKuhWPdDsW419qmPi9j1dhvJX0zHY2g+6Em42o3QpWgV0ViRlLKRtmnZ4rdWlo5iEVSi8rjApFi2cuxlhV91l5SypRxJdadRtio5zeMWc/aMkuebcnm5ZIUstM7cJoadsoCNTE9IOW6RIsrmy2nFecBKSo5tOYDEw7Y8pKWc+y82y2wqZcTkaZnFkUURpAizbVnpplxTSmmJNzEg0wUQE0O7WkWOZaybKmpxU3NvuhJlrxLyWskKlSgFY6OOEzEs8pvLsiZbuZdn4Rs6R5xEs6qbUlKCtuYdaqEdrghQGEWayhxhQdD8y4pZCk4hV5xRxEOJeZeSFocQbyVJOYgjODsphuQsmwrwyqjdCqXpdoHdKzlDu9rFiuTro/SOpUo45jkUYgf+4pENBFoziCiUZ2iKIUsClEFQTVVM6lRZ70vXbIafKSDulIClp+ekWOploChdRl5xSjpo8U3Uj5KR88WPOTs+nbX1ltaGuNEvUHDdKSqHctKzSAttWbDvHN3oNFS7zifFKH/+KJzKTQAKpeXSXXE1Fdtd7X96kWBMTlNKnEnzMh0+aLP9jpyUQl26FFQKFEpxvJQoKBGIps9639omPi9j1NhvJT1pjsllren55lTq1Ic0qXgFXjmUamqRhSE2nbU9NAXZOTlVNybCQczbrwbSo47ZZXjoAgFN1N5QzkUFdGeJ46m2rJ/ygTbYIDI9udfSrFIdGGggRPylrWLItkPzcvLlpsvnM22q8b5HdUwEG9bttr1tIo3u/dPE2nGJb2VZyOUmp5Dlwsh1jB4pcNVDA3tteqOOGFey2pppucQgDbrYV7c3u7ZBrSLcyMjaJZDzks31xp1RqApxWHag7SmjHPDloWvatnPqbmJW13MWlDMoMI63dUMQQIQlpppIShCRRKQMwA2Pby0s4UDdWRRI8MOLYsmRKVTLqdK7t1KUaMoU913Ke/EsiUl0aE6eNRzk7pMPa4es4a3Q4gC6qYU8koDZOCqKFK9rX54mFWjPPG8uXvlSK/rV4Ff7Ao2M1IQlppAolCBRIHEIAS7Pu5RSroJqVpab0ZgVFf7sJusSiLia4k7pJ3ScYqpmxU3DdxvTDgBVTjCaJHfMS6bQtJ3rqm3dsy0VY0Ccyl7q1Y13IabDyutyjCAE5R3Rm7kZ1HQIqqZts1bJwKmqlZcpoyi1Ej5NNnvW/tEx8XS/qDYbyT9aZ7IBXqzj8pLuqGXyFAXW9LSiQdqeLGLkxIu4zNkTThCSaduw4a3V7oOBiV1rMqbClNKKVqaUobZN4YeCMk/I21Ky8tkKVqG79+/oxvQgNMspCEITmSkYACJdLZXabk0vJooKNMLqpVBxiC9IWm2m6iek15J8DcJHbDiVWNVEzacgEKSZaYZaqVHtTlEAHDYpaWVTKFvpdVdqlGKceJdCeKMQynri9LjisVrPGTD4lpuaZKG1qqB3iRiAcxIiZl2peTdQ8JeWJcKy2byBeIQEpqBo4urillDGH+nMr/2HUlXFWe3aDk8uZ/RuIvFxoA7t66KfJMG620krWeJOJi0pazrHlxVmVmXQ2VN50sgHQe2eVpNE4iLXZmbowbkwXzTvNgxLTDWs0hd925RQJp3JN08Ssdl8Gj7RMfF0v6g2G9k/WmPeHC80yE9/IP8AVF5KhQg6REq/L3u4ZmXEIHeTWgHEIs4zfFNPOPJ8VRpEu3LMpzIaSEDwDZfBJ9dMfF0v6g2G9k/WmPck01K67dDDOUVdvuKzJTx9VxTbN9LYuJK1KUrMAkYmMyhXw9VQQ22kqUo5gBiTFpJ1yv2tt5KmVODdRfpeHe6pCUIFSTmAEPB+WmE3m3E5lCJy41LBrKS9zFamCot0XXDFWOGiJpuYekF5OYSg1yajoOyNGJNsuKpnNNA4zmiRMgpD62bl/KJNzSlWFdw4ZwepOOpkEoS2tgK62QWHHFVTm7a5jn6nwI9cR8Wy32Y2G9kvWmOx2lLyGXrk8usJvXc9O9E6zPM1pfZWFivzQaAZyYWlxO6k1Hmhhv2GshSZduYFb65ge2DcoP8A7nwFVSrDjoHGlNYmteTk2lbinLoTgVG6MMMB1FqSxatsS7D900JRWucbhxjuLflUjwHqTDiZexJRmWaYCjkzldsVFOavHGm2pb0xLPzaJelWpZF9w1NMB6YlrS8lVEtaR/8AiqgbR1IVRQpgoaQYlUUT7S4gXXGVDMptQzUh3XM7Yk49Zzr/AMLkTtVd+7SvHEpMWtaz6b6ZOUFVBG+Wo7VA78SbslOSwyc5IzQF8JXhXDBSVboiZNoalJuZbltavmr8mp5V1OSX3SKntT1AOu2rPmu7149S/l7WWttigqKtpvmp0YQtQQ5Zs7eTXA0UjR1e6mpMfTojupUKPfUSSepu/wDbdT4D+YR8Wy32Y2G5JetMdjs2Xn8hXJ5dsLu3s9K7sBuQkLUk39fspN1ptKAVJcOhIqMPnpni1V/4elWMo0wxVGulo9svH5xSujFO7Dr7MtbGW13KKcLiLiUmisePH5sNNe39mZq936xoknvVjgo9PU7SUtyXUs7iTGb/ABBLHzK6mBtez2JpHHk9qYSVBNtStaCuc06gEARNNyksjtnHVXR54kHG2V7VdsTqC2y2NJaQds4qmbRClu3SVuvOdu66s1WtXGTHWT7FOTLpb2pdUlQbRlKduAFGgMe2GxHddU3uVGSr56Qm9YjL7oUT7W3OKAyC3OLPQ7sdyhSvNGeaXMTHKvKV1OFzX3cxmmJWfYH7VEq9A6v/AEymJg/stOpUrzCDUyYXKr/aaWU9TC+aDyU83U4P/MI+LZb7MbDckvWmOxyyZy0UNnINLzKV4R4Kiu7Dy0F41WwFDLOUzAlBKWkDQlBJ4waxO/4bnrKbUyythoKRk1aCnDw178Wi7bdtuoyZmXRdCE6QhOiv/wCRUFu0Net10pfFcO9hCihE4ytlSk5wFilYeL6ZFoNhxQoVU4hDb79h2hKIyTl/rLK2tF2uCia5h/aWRNyylJWW15qpNREo1NJlnEvNBxIVccR2qk7hESuu3G5hvLJDZdIZJ2xCU482eGHET0qz1kkqQQl0ZnEaaVzHNsW1rEssrbLbimykkUOKeKJq1EITmCZ54AeeJmfdcuFF2YmnHUUV8lRpFo+xlrSKFshS28q060uhKHE4HOMCDE0LYt+0E7ZdMk1dbrk2WxjdTXTGoluXbnGsm6ubnEXEk6U3Lytqcxh/Xk5LSWQLvwiwilcYZVLzMvLhLja86VVMSr7GsXEt5R1NEO3khV5o90BWh44YQ47LKKmVqSCpskXSUnQSMISTMWU6p2XUk0oVJumu6KRJeyE4wkFDOONSATROJujGgxMSXsfOPpJcYNcMaA0OIvDGhxGmGw7LzCFNuIVmUlQoRGVKH3cstTyr6iaBI8CQB1JJk2khGTEzd64E7lepwf8AmEfFst9mNhuSXrTHZ5VtM882Glv025QMwr7u4P8AzCPi2W+zGw0pk/MqY97e6bSnwrTHxdL+oNhaTVnzkg3cCnWy5ikm6oY07o56xq0l/I0xq1l/I080atmPJE80auGPJEfhjVyz5I3+CNXDPkrf4I1bseTI/BGrZjydHRxq1Z5FHRxqza5NPRRqyb8VPQxqxb8CehjVg3/D0Ear0eboI1Xo8I6CNVqPGHQRqsR44/p41VI5Qf08aqEcsP6aNUzfLj+mjVG15T+VjVE35V+VjVE35V+VjVCjyv8ALRqhT5X+WjVCnyz8tGqAeWfl41QDy38vGqAeW/l41QfXvy8aoPrx/p41QfXj/Txqg+vHoI1QfXz0EaoPr56CNUH189BGqD6+egjVB9ePQRqg+vfl41QDy38vGqFPlf5aNUTflX5WNVCB/rA/9tGqxHjj+njVOzMyrtL7a8UmhqK3WknPxiHMsJCXQxfOF64KV/8AM3//xAArEAEAAgIBAgUEAgMBAQAAAAABABEhMUEgURBhcZHwMFCBobHBQNHx4WD/2gAIAQEAAT8h8AyPgTf2Jlges37hINY/WHSvw8NPfivWLG147dg7teA3Yuug7fv/ADSlxteMJIS7UYAshNs4BsL8BSs1FCa+h7N2DbtXolbBW68ZbNl2vGgRANr5vnPkv9zLXwPWWOTowxTxhzoIqS1rEL9pT+VP0BW/mHW8ush4frjOvXq075cvXp3+jBnu5PHPBIX8r4gGyp2jPAAYA/sh/wBqP/Zj/wBGZzJ+fGYM+9GnJePvnjhu8r/SH8TZsez+IDcr8u0+Q/1AClfj26LUks2iKy7Tpw685dIsigBauVx0yjX2z8sXVyajX21R8326MI8a6vInrdJX0Fv9T4D71GM7JQjvat0zd9Ab0Sz7H4+b7PQ8EI104oIVB1tsutpR5QEvCnnW3zXocs9mzEhoIj7LXqBysCGDlv3szAlD9t4Hk4ly4Kkd6tXrrFMsV9UAmUG58IrFJgBLHIn2Fx8326PQEE10KNpFDuZxa2thAFircZMnBCsSgW4rRgjoFAcHTfYOmoVWBw6uHgJLIdBLqi63Ug5Zbn2AR8326EUkU14+kAlfDO6o8FB7nQPWe5PbAXQyAq+y4Iouq2wTKKQI0nY+wERXycdCj5rx0Nh+aUfpETsW/lAp9BdZ7Q9sflGiHFvoQ19gI/AdujejGvFqkWa7r/TANFUUAaPo3bJRDsAfy/BDX+eL+A7dPU14mWpCEE2xRVboJ/gfRHBL+zYHDk9u8NfZJYEA143QvLl36GJlEvNbtbevL5+gxwlKLgX0JZK0HsnpUGfZJrkUpNeJ9jqFAG1ZavQxeLTbQ4XJsuCNtLxMm4MMRpDIhSPl3mEXArbQYNfgFAFxwwRZtI5MluaQasAj5oCr3f2Unpaa8QJaQOZ0ghFQMAQIzXLf5Dwos1SBF+ZZ5cdtOEz/AKniEXOYMgOc0BBJRSN3ZajN0cCRyN1QVOX6py5QDzUUykflZ7/ZJCm57ZmuppXdmHMAxqBtlIP0Pa0NEwTFvvN8QB5Tpez+2PSWhR7k9fxQkYs5xZad50eMZYuVpwdZZ/KMZ+//AC/55C4N7dLps5xrxZjvhAAPNiEdibtUbsXTjcAWN+X+534zpfZRVEnclTz4YKITFmJAtnhnRylTJuKTP+eFo6bCwHb9/See955z3nnveeenmp5jLe/ja7fsgitYu38f3KSZ0/tv9It7dMbpfO/ts35b0x54x+3SV8L2dC+o5dLJnJAclQ7Kl0xkWgbgqcjkmtzS6HKzWMDnpUOerCmC1cxg3py6GWnRO8rCVUtmtgmbhTnpVrnXPnGXyDczBcQGjTSI4RyO/C0c0upF0AtdgDLEo5NTrQYRPGlLNfT/AFZxXxMehKOPp8IyqkdKmdEpamZhwrSgylVcXozkZZ0LynNnPIBsG0JmNIWsLGLVAhM84FZiLJyhczx7sUx04KZeibHJ2moa4uAu0dGkykUYFa7vgvwGzYTZOJhS8srxcGFr1AVRx2QbQ4BQBoDgOlztbViavKAMFc87oNQAA2/UFlzRouWqE/ioSVMV4FjcgcKcTcNqeBwNvowJFtVNy/nBXpHTI4NMmgEOqMm4yrCCBALLPIS7QykafpORXysei9Wj9n09BuQoRlYgWLRqVUQdIyLeMpst6usipGnqQMly+WIL/SPqKEVxRLOiwVDu37RlJiHsiuhURYCaN8xwaods/JmzDN1Cw016kAEYoc0tRlfEzMraO+X3CZ3v6o5bA3UWPgY3mYF3Ax+bC3bgGROn1xBPstcXNrEuxULhEOovnzQvl1geHSudYHvubMkt1pqjuLhzSbN8xQfjidYGFKa474RIbgrVzr/DOaKMQXQBjZ8NCjSixxAfsC9iX3p+Zr64gkrYuO1DoB0BPyLjSDI+E2yhqnmHXHFfDw6Ek2Wfn6bpuOqTYBYI1ZUOxkgSmcHY9JmL2EIaOQqWALWOxviNvWuBs7gDJBN8OS6F7yrAxtlwrhOVxs517xdESZQL0Gn08RWUGkW/2AYx05IDKhCd2Kzm6G6EPPUW2jhYhAb1hTu4ZkCRKgMAGjpynNZr9pmS+usKQIHq6y2MmBunXStrcraFXLHPDpmiQaJbavFKG4Kxa61nOG5lC4U9RArgMBB0yCBcRkqpZeLi41wSwo2pltJ7sdxcGSibnCu0oQfiw3acLjLtVMS8i10QwwxwzCsbwEe2OFw+632jdMAVfUwBrDSgpLaq8eDwKg6mjELctcN1EGwxH1qk1HcDK5+ECNuq0qpmYGVqnFLkGhXEPbFlE0XAGJVWOvNkIuyzu5ZANnicEfYExxFlIwmxcU3fTTotjEudgl2hrMLzJf2ELWLFXqy7vScBYNkERMGEkoMQahShY5befBSb+PQiP5PxHSL1i9CNVGytuWqUWYS/I4nhKexAGpWSp0L0byEwXSgVFYAqA12JcvdzC5j7jFSOMPV7BOk9ZeV/iaoiMn1XpMkd1P8Ac0eBn0ALEKROyQfer9FF0XFAT2SdLz/6wzeADQ8kOjz8Nvt0teX+GKDZR5xOIELu22+DwzVxJRIqXgqm1rQXNToFlYFmHxXy5tD2HsBc9RbEACbwWNr8U6oeoC1XsEzxuRwbLphoXt5dYjWpzLgbYPsNiCs479NOOpgw/wCAx3loebG7QUMFZygvMKIWUsdLbCwXaUI1VhBYaj8immvr4oHmG8q4sbvIv1lJ1tEOx4PrD7Z6AD1XBDChgF71iXB8lZGz2BWAG3CDlshpUL/Mvr0i9ck4V14AoPLYsqhGkpyEKjtDloHf4J+6zEGkCZdMLel67Rz4ipa4xgv7pgu0tBlhXhPaOgp22xu9EtMitJpyMwxEp+KRXxDHE/CjQkrbaW3leblZhAXsDgmsLL4lTelw6MqFYJMKqARVbJ5MgTAvhlVpVJnHFefBvRv3b6MYlfQlV1FdLPHzHj3v6i0afMzA813Nkzv+bw1P0/V0Z3P0lUulNGlWMFC+9ExaEG+CtjawPIrdJTkMBcrBehmsRlYyylkqKKLkcwJj/wBUcG55xP8AeVfunumU7lH/ACtch9qjvTLXFbJl6zO+j64f4H2jNZG1MDXdweeJsruz/iE/5hNAbWFdF7LwGWZL+gzp5kL1lLfhYWwV3X4MQzUXmr48tOK6dhBqrPcI+re7i6jaKcbQEeGOgUHKj2hsK2vx0O/w14MomhuVP4P3H8Q14AhfoNS96BH77BVfqZ9JsiPqbeVQD8L2hqC/SdL/AOJE5+k2ohwapd21vgKpdwOS/wDdJ8PDuMqNDKi55SsdxoypnAytZt0G1oxQWBiK4Fo82VvUlSXgLSheLL5gDuId40HpH6k1UQu2yVtmG3MXKyAtecI4fcw4hmijmBa9EkexsuVJCdF5ytgikJeUoZYutpYNRGs5zroWjZ5uwhdqswym0bHYCpDBD3R43rYw8Q6YaS+yMAIH8VVGPgQJFz2clcsxbRh9x0clAmGKCJddr5kuXFrfeVeUuMwGljO+ei9S7QxgnEWt5JOiLZUnDUTLJG7GDYtR+pF2pSY37XaKMxqFaBi6KGATAaygGtuuzI7Ixg1fKRqjATlq223wvwhx8q3rKen0LP8AUd/WKQTzf2vYdQpk+nR1ek9epVbArXo9DdzDZLJiWSyWSyWSyWSyWTExMTExMTEsmOvMplMplMplMplMplMpmfGyYhP/AA+09Fv2Low5WAGSgsBoRmfDP6j23xC/s74xJJafCkOJHoEut9hl/wDF4XhVf9EL5ysWx27hJmqxGdpsiKDYeJVv9WVf9aVM/oxtvbSPUcamA01P/wCslC33s+S90XxKYm06VxBxR0UULh7o1X5rS1f5N51J+oZpPnjsnCX5YyPZfBqi9Acaop0SK1HBxfb/AOm//8QAKxABAAICAAQFAwUBAQAAAAAAAQARITEgQVFhEHGBkbEwofBAUMHR8eFg/9oACAEBAAE/EPBaY2dO6EC9iEUPsZHGx6KX0o+jhJGjdlGO8cCVBYuf4OPbRQDOIBHcwTMINIQbOyCtDSZqlEtyG6tdIFKV5T47gki3LObBnvnnUhyflUqNg6EvO1RhLTeY0aY9vD7xNQYmpMfXjsYUE1GGo8AylQC4J7mwxsHygFaJATJCIUt8OzTGYtBuZACCORMnBea490qdsCVl8mwUQMEjXJwPcjY4rVMZ1m/bc5HebT0fe+A2bJuhLB78ZMmxzvb4qijnxNm/avCV6MoU2l7B9otWz0viRYUKpG++FhDrgE9y0BtPSYc6uKvX/FANr+PKa4k6pID4a+PGb7hFJ70/Eg39B+BC7KOlPxaW7gVN2T7UPb4SHpH0PUvgy+AdGUugBCbrEHtvz6THUGuCE3JVgiluY1RMhFtE3phZoZKq8+C0W1+X7Ysr/tP8Rqr/AILgpRudr8v2x0XUZq/ysuBnkP3nr8uIFQLXQQQ6tF5dLe9iK5e6R96KresLBBhHluPUcoHAAEbpWh5P7Gdtm4IW7oo9Y6/LhDCECqG+AFMFfkp9UnylEiUCpOHoqyC/Bazm9IUNiGdOj1TJPVhUGw2OlM2pQUhY1DTiCa2xLHSgV8Q7u7sFajrcl1QOQBAREsRMImRMJ+w3aYvyXADzaff+qa/LgJOI8wlkDIYCMYqyidVNeC3qMVAGYMIAAoAMAGgwcIKXVm4jRJoE14kMBtqtvF6mVkIpGkpMI/rzl6LPL8rLgvFku8mGvy8aVoWuA7w9zEoaWi0EcAy5A4zMx1R69FDw4z2wi+8F4DAmKvVlfr9zAer7vgGk2W+s9fl45pzI2CXVaAlcCBU0Uc8o6tUM/QEg7VnpKw0I4Nyqb6sIKFfr135Lr4L0uRPrPX5ePVwPQVezkhh5iUsAYD6LOpjQK0Gxbr59LGvy/XKo5j+NnwYOaqhr8vEB5sWtr7CfSJiD9S5XmpH6DjM1BSbRZjGh7LSUGBAPI/X3Pw3VwYrvD7x1+Xiak5lgqTzIIgu0zIuW0LgSbLxkAFc7PMANKfRxQpouyQIZjy/Xb0fEfwtwWWcq/W34Jr8vEhFQB1oYABVcBEd4oaD3aWFcZA38icjA9QQMJep2TwNFil0IUbN2kv5uhAlbqvHgIALZXKBYMm9RiyLCLJVDqhkgW39eM3R50/N24LZXA69Ya/LxDYUSOkmV3YpVsYiNGiKhpCuBl2IPKWSq+tONYiiRCMW5Asao8FfdjXOCjRFq0KAZyxINKKSIGGyIAvy2rgisUz5lsrbRZ4Mh9Btu4S2s1j9e4YKE/FcFXJFfrf8AxNflwd7MMS5SJzGzJbUNW6axM4KQ1XM5uaQtDLPC6PagHrhwbcto0KtumnPGFWorTRAAAc1Uqya9/PgQkhQWZ7TR/YATQJT11eel8N4Aqgr9Y6PLxPHCVlLG0QDrG7DEmdopuIKNKUxErOVV5mksD08ngIvXEsCZrqXn9o3RqLtwQhpGBCkvOLWXuIgnmhtZdGIS+FT9eEXwrXQu9OvDSVyj8Z9IaPHWSf7if7Cf7if6jF/7Gf607r89JnrM9ftEQIhq39ekUakgKOBE3zK9/wCiGj9spnkhAeCJeg+9/wAQ0fthW/7IzW/CvAxrZPvf0ftvnA+0vznRwVZVlDkPy5/bQtHSYhXwMqTo+5/T21BrmSZeUgJ8iLJGbVTWuUUSlZhzFBeAdYJzlBxEsaAjThE4cdGXid4ZFVgmRz4tWFSr3EUFyv0h8qigIDaFw3wWCRyw9oCioAVIArB0jEsyhMeRwCATw6Tv/wASQzSqqALDKsRAkJSCI0niKEo2hj3+ngpym1E63vwDgyiPNB+Y4a+krvkxojrFKFEAsqYh39okhKGpvTR2enpXuN44KAHfbgTohXxQVMxxeirJqCJkHahmP3QaoVl6qOToC3TKfNuSsAazUuPdOzBqdzGGi2OQsO3lyoQkKSRFPMUk5bA+b5AoCFEA/QFACgMBjhvNtlUd0SQIqaRzB3QhZh5YFJUhEDeY+pgBBQhiwgaul6wRMo6PCAPmgEAEevajkA2hPrHQ0mvFGhyiVVVY7dUp10vXHcBYGYIbNT288j4IIQKGJGnzPpB6h3ffg1U5Qxz5r0I7fpPGPg4a6oRQbphLDeF7fv7xi4iamF31L7Dqb0JtjdSoU3tXxRBpRK0e+LCcUaKSgolyoaG1ZxaFlAtMzNBuAwm4oKncqcmL6NcuoVPBI5OOSCvDKVZFEiGZmgQTJ6ygHN3cLG8TalCiLVEGEZCRScFAQRGzhacIHNdBzfSFqshS8Fw12LUA4bD0CxPYInSqati3sotVe/TA0hpQgFSVDniAlTIxFRkukBqgCg7Bh9NF3RuSFiakQhojVfFpl4nhKBJ1gfbhRTaETG7yJgBdMdXx2AKkecu6AAOmWY2XrunZfrfvwFrZTWLvjFbXkvtJgPX9+BvE8saHwx2/SFAbGz0mdlwO07iqIRmDYiapArhepikSoKg2cGwhC2xVrii2KeG13BQzrq7JS5WUq7Rs1BNEo2ZgpC0BeMGhKu4Yt7iGYeEmYgApkorwKcVgaJMrdYM+7FIBVphn8uPC7Koq1uxsFvBgweDgAoOEAhki1jQWyihb0S+se1SDYzCb8ih/Y8PqSz22BS1ZRP8AiJrt7EAExZFAC4YGjfIoRASBc3fyoLAHIJXsomLsqJg5gQIi/FiqipSbUhIrlRZY7di5HSKaWajrPauUrOhyzvd3a7ymWEgtCOcc5GsXIe1Vhxxksf6RYENK4Cv50veTt+niRL5CCinpF7VTlePaGNQzrzbUI6nMxWQXebi96k6V2xGwIKReYkyhfLXtIplo1CotEk27WHMJ9pycTgcB0IRFXqYAjljtAJVWkKTeWRkK6pFIGBE0AjuYrBZKb4LbKRsqBHXebQQmFnQ3XADpgAoAAA3cEfWgw5YOgpB85C7krSuKWCU3JX1z4KYzSGTDu+eLyg5NSL3ths2fJk5PV8DYScCJ9vEtzpKOsf1GmgJhgUtrVyK2C7EoVVWawCkQHHFEsd9XLxogcJt/6IXhyMs8l9+O366CUwKweIEGylCy8NOy+db46vfC6YGNQsBowuLaAc3BPsDwCJbxSWYUIjsZcuz6Kg6AADABCqoRzKrHJTrvzuD80+ZQGAGscFNYNdeXv4euB8hIAq4PoQxOdv6ARyeCERbUBnu4iiDlVzKmLg245xEUdkrQa68phhOhwp4gQbWhHHVnRILAEaSxBNPjb2F4LyEsnoRuZbVsNnxFaBTTWTD4A5D0iPsAFXoQdHs6iivRYmQg2TUWwZcUiFguu1M9mYDSIiaFUicVUKOXXd5KeYRTuIDyA0IcDJiWhYUZTy4FW6A4xCqwxxuY9o0I6nunBV7cV2/SHtQIlCyQjQUIHZFbwCrbdLi0CHj6hP1QB3WLGGyxdF1nrD+ncBG46VCGRpqVIai9lC+0QtYKiFfYKhYHgEV+lKCrYoI7lGWU1ESwrKuZdpbFSdPmYcL2V8saWuQIWhAM0Fe9JLr11msKxWlBqAXGlRGVePRrpAiFihNpyLot5F5cS9NYtMJl4A1jYk0ysiKjD6lAi0Uyp+OFCp4IwhNBQAIvB34pqyG1WjTFsLszeLdxjk0y3qTL0JpgsHsVtQCWBt1phgg0gAcF0Ci1o14Wl3fcwNArZ3b2FibUQbqSj5Cl+Kcg8lfzIDwmTbIOarTlcs+wYuaUA8v+00eUJbzFPMohQ3g4uGC7eb9InV5MRBOLxpImCVy1ZtA8IylyQc15LsnCJVsgekqQ3hEd1GYq7BNBCueQdszzVU9Ye7eGqdUFhp01j3h1beMFG0XSi3K/bClV8hqJ6ZMzJ9fmZRpZDYqTq+T0VaZT8PRVtq1aWbQLUFJrYPvPxr+JVk/K7SuPtjxVgWsWkaC4idxj2BUisE1VqH+VyfI7tZC6oGCPB1U1pjJtnBUBRtgNMUmK5+fRDDpFcEGMFKWGWti6WAjdlyPeruWQIjnSPUhp514FUltJeYlHS4wNF3qEdF0isPbwvF60Kj8GVIRimQmEBySD1Qk+wYqNdBDwOarrdJ0TRGKdT24bg16A/do7eb9Jet3Wpgglb7/JTJceE+pA4wKuHIiluO6lRrnAipTpFrgWMpznOjIahBAQTbXAYGs28GmHpEklWQApqmA6zjCW9g20WUKtXMogzo1DTEAaNLSDLtii3KwZLpEFJDDUqcQC0VM4FNMKpQ0QugdMBQWMcMis1UiDEDrLgoqgAKOiWOTk7OBt2OaiGsKBq7KZ2oQunYHYKhg5eHixIaWF7ExsPyF1SkEW2OUEH2NVaYKhrDJQRxExQNi2QZgM2DSg0AJ5EwUsKtge6QCIUlt2Z0l1AyQw8f1yHtKiFya1DHzDkVlhYEWUJv8AJm3i6ezYsoOZMmRgdexCN3Q6KhJsGkFAVq0HxthWGKqEDTziGEVFlUjulOKRMWlAOxINKeBgTXBhjNNnn9JBKYAFHiSq4bmo63LbRagV4UXfijaj1MMVcv0gNFeNvN1rxFG1SaTDFVtWu1y8RKXKe8eTl4DgK4YH918RsfOdyXHcncncncncncncncnclxcXFxcXFx3JcWeNMplPSV0PtOwzsPtOw+07D7TsPtOw+07D7TsPtMtU+07D7TsPtK6H2lJuWG53JcKDX4w9YZDdfgrpwHs4L7jOVxKNWFPL/wAmUWFjmYc+blK9JfL5b2lJyl6PPuvL8uVCx1sYo0u92EaqB3ZfzicKOeBgp88/Yb/MXlq5kVLSnc/mZsMPQZ2HoB8Q7Hopn+GChT/FTXsXSiT9ilX3gaSelP3/AKQKhDBEXvnXsyobWNBXP2Yu1Y5/yjO4XQvgwwsfQnwIcwPSvxFus/fNeYz20BfEBzblJgNYQJj7BfE95EFpRq5VH3EWJ2dW+XNH73BYYvV+SgIPydPNTB134SoYm3dU/kjqyeXQfZcoIho0ABFVYtFq4QjWN0GVLBSzRWV/9N//xAAhEQACAgEEAwEBAAAAAAAAAAAAARARMQIgIVEwQEESYf/aAAgBAgEBPwAo4KXZS7KXZS7KXZS7KXZS7HRwcHBwcHBxCSKRSKQ41YXracjzGrClI4KQ16GnI8xqwoSG5T7GvPpyPMNx82vz6cjzDj5tfn05HmHC7K+nBwVQ1XnWR5hwlwJMv+FP4h6Wa8+dvgeYfrvMP13mH42q8jz4EIoaE9lRQihrY8w1W9MZexQ2JRfZWx5jV6Ciy5Y8xq3VHw0jwVzLKGPM/IWR5jVuub22yyy5uFkeYfMJFf0aKKKKKEj8lH5KKPyUUfkoooftf//EAB0RAAICAwEBAQAAAAAAAAAAAAABETAQIUAgUGD/2gAIAQMBAT8A5NmzZvK5nlczFhZjjYqVexdDqV7F4kkaNEi/CL6S+67ldF01T4kknu//2Q==");
        params.put("pic_fmt", "jpg"); // 图片格式。jpg, png
        params.put("pic_type", "102"); // 图片类型，101: 个人身份证信息面，102: 个人身份证国徽面，201: 企业证件照片，202: 法人身份证信息面，203: 法人身份证国徽面

        UserPic obj = UserPic.upload(params);

        assertEquals("user should be same as param", params.get("user"), obj.getUser());
    }
}
