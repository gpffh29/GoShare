package com.GoShare.codefAPI;

import lombok.Getter;
import lombok.Setter;

/*
 * Desc : EasyCodef 발급 클라이언트 정보 설정 클래스
 */
@Getter
@Setter
public class CodefClientInfo {
    public static final String DEMO_CLIENT_ID = "29a3f933-adfd-4973-b92a-1879a4a53a73";
    public static final String DEMO_CLIENT_SECRET = "d67f3c6f-2a83-4220-983a-ec9ae6af0a7b";

    public static final String CLIENT_ID = "ef27cfaa-10c1-4470-adac-60ba476273f9";
    public static final String CLIENT_SECRET = "83160c33-9045-4915-86d8-809473cdf5c3";

    /**  임시로 설정된 PUBLIC_KEY를 제거하고 코드에프 가입을 통해 발급 받은 본인 계정의 RSA 공개키 정보 설정 필요. */
    public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyQgGGPuPz35rnoqggmqtZxZXQIOCs2RKAwqR/4Kr9bhouI9riqnZyb5SLv361uEGwY90ejJNqj99JpUg2xByYvJRrELcMZQyjrajap2TTy5L6VwZPLl0xNekUqK9FqQ1OeLf5dAF0HU4szSg/84CcsTcFTdqbVr0hSneGcxnL+1ylxXEt89clihS4J23+uLrHK1F+eRCg7DE7W5BN68yPmK8UEdkT3vUkSQ3C//H3KjMolVPpNqZfIVphHhoSil8a4RhLwqWHrpXPCacRCIMP8YNChuF4CLc3ohrsT/1mtOBTA09Bt9j6xDY74k9wqnniI53SkgrLfH9pWVSdNChHwIDAQAB";
}
