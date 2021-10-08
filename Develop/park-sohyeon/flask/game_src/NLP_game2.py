from eunjeon import Mecab

class NLP2:
    def make_important_word(sentences):
        m = Mecab('Develop\park-sohyeon\flask\dic')
        mpos = m.pos(sentences) #문장 내 모든 단어의 품사를 구함
        important_sentences = []#평서문 들 중 NNG, NNP, NP와 같은 품사를 가지는 단어들을 추출한 리스트
        Str_dot_sentences = []#리스트화 되어있는 문자열들을 띄어쓰기 없이 하나의 문자열로 변환
        for letter in range(len(mpos)):#추출한 평서문의 개수만큼 roop
            Str_dot_sentences = ''.join(mpos[letter])

            for num in range(len(mpos[letter])):#추출한 평서문 내 첫번째 리스트에 있는 문장의 길이만큼 roop
                if 'NNG' in m.pos(Str_dot_sentences)[num][1] :
                    important_sentences.append(mpos[letter][num])

                elif 'NNP' in m.pos(Str_dot_sentences)[num][1] :
                    important_sentences.append(mpos[letter][num])

                elif 'NP' in m.pos(Str_dot_sentences)[num][1] :
                    important_sentences.append(mpos[letter][num])

                else :
                    pass

        remove_list = ['어제', '오늘', '내일', '그저께', '모레', '나', '은', '어저께']
        for remove_word in remove_list:
            if remove_word in important_sentences :
                important_sentences.remove(remove_word)
        return important_sentences