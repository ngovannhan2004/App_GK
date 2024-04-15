package helper

class Helper {
    companion object {
        @JvmStatic
        fun normalizeVietnamese(str: String): String {
            val original = arrayOf(
                "á",
                "à",
                "ả",
                "ã",
                "ạ",
                "ă",
                "ắ",
                "ặ",
                "ằ",
                "ẳ",
                "ẵ",
                "â",
                "ấ",
                "ầ",
                "ẩ",
                "ẫ",
                "ậ",
                "đ",
                "é",
                "è",
                "ẻ",
                "ẽ",
                "ẹ",
                "ê",
                "ế",
                "ề",
                "ể",
                "ễ",
                "ệ",
                "í",
                "ì",
                "ỉ",
                "ĩ",
                "ị",
                "ó",
                "ò",
                "ỏ",
                "õ",
                "ọ",
                "ô",
                "ố",
                "ồ",
                "ổ",
                "ỗ",
                "ộ",
                "ơ",
                "ớ",
                "ờ",
                "ở",
                "ỡ",
                "ợ",
                "ú",
                "ù",
                "ủ",
                "ũ",
                "ụ",
                "ư",
                "ứ",
                "ừ",
                "ử",
                "ữ",
                "ự",
                "ý",
                "ỳ",
                "ỷ",
                "ỹ",
                "ỵ"
            )
            val normalized = arrayOf(
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "d",
                "e",
                "e",
                "e",
                "e",
                "e",
                "e",
                "e",
                "e",
                "e",
                "e",
                "e",
                "i",
                "i",
                "i",
                "i",
                "i",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "o",
                "u",
                "u",
                "u",
                "u",
                "u",
                "u",
                "u",
                "u",
                "u",
                "u",
                "u",
                "y",
                "y",
                "y",
                "y",
                "y"
            )
            return str.map { char ->
                val index = original.indexOf(char.toString())
                if (index >= 0) normalized[index] else char
            }.joinToString("")
        }

        private fun extractPrefix(inputString: String): String? {
            val pattern = Regex("^\\d+\\D*")  // Mẫu regular expression để tìm tiền tố
            val matchResult = pattern.find(inputString)
            return matchResult?.value?.lowercase() ?: "22it"
        }


        fun createEmail(hoTen: String, msv: String): String {
            var emailResult = ""
            val normalizedHoTen = normalizeVietnamese(hoTen).lowercase()
            val arrayName = normalizedHoTen.split(" ").toMutableList()
            if (arrayName.size == 1) {
                return arrayName[0] + "@vku.udn.vn"
            }
            for (i in 0 until arrayName.size - 1) {
                emailResult += arrayName[i].first()
            }
            emailResult =
                arrayName[arrayName.size - 1] + emailResult + "." + extractPrefix(msv) + "@vku.udn.vn"
            return emailResult
        }

        fun normalizeName(name: String): String {
            val capitalizedWords = name.lowercase().split(" ")
                .joinToString(" ") { it -> it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
            return capitalizedWords.replace("[^\\p{L}\\d\\s]".toRegex(), "")
        }

    }
}