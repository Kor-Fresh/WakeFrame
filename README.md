# WakeUp

WakeUp은 화면 꺼짐을 방지하고, 사진 확인과 특정 공략 이미지를 빠르게 볼 수 있도록 만든 안드로이드 앱입니다.

## 주요 기능

- **잠금대기방지 On/Off 스위치**
  - On 상태에서 화면이 자동으로 꺼지지 않도록 유지합니다.
- **사진보관함 열기**
  - 기본 갤러리/사진 앱을 열어 저장된 이미지를 확인할 수 있습니다.
- **`아이온2 침식 3넴` 이미지 보기**
  - 버튼 클릭 시 전용 이미지를 가로 모드 전체화면 다이얼로그로 크게 표시합니다.
  - 우상단 `X` 버튼으로 닫을 수 있으며, 닫으면 세로 모드로 복귀합니다.

## 실행 방법

프로젝트 루트에서 아래 명령어 실행:

```bash
./gradlew :app:installDebug
```

Windows에서는:

```bash
.\gradlew.bat :app:installDebug
```

## APK 빌드

- 디버그 APK:

```bash
.\gradlew.bat :app:assembleDebug
```

생성 위치:

`app/build/outputs/apk/debug/app-debug.apk`
